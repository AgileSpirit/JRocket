package io.jrocket.infra.monitoring.servletfilter;

import com.codahale.metrics.Meter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.*;

public class HttpActivityServletFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpActivityServletFilter.class);

    private final Map<Integer, String> metricsNamesByHttpStatusCode = new HashMap<Integer, String>();

    private MetricRegistry metricRegistry;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.metricRegistry = (MetricRegistry) WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext()).getBean(MetricRegistry.class);

        createAndRegisterMeterForStatusCode(SC_OK, "Requête traitée avec succès");
        createAndRegisterMeterForStatusCode(SC_NO_CONTENT, "Requête traitée avec succès mais pas d’information à renvoyer");
        createAndRegisterMeterForStatusCode(SC_NOT_MODIFIED, "Document non modifié depuis la dernière requête");
        createAndRegisterMeterForStatusCode(SC_BAD_REQUEST, "La syntaxe de la requête est erronée");
        createAndRegisterMeterForStatusCode(SC_FORBIDDEN, "Le serveur a compris la requête, mais refuse de l'exécuter.");
        createAndRegisterMeterForStatusCode(SC_NOT_FOUND, "Page non trouvée");
        createAndRegisterMeterForStatusCode(SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur");
    }

    private Metric createAndRegisterMeterForStatusCode(int statusCode, String metricName) {
        // Générate a registration name
        String registrationName = "HTTP-" + statusCode + " : " + metricName;

        // Link the HTTP status code with the registration name
        metricsNamesByHttpStatusCode.put(Integer.valueOf(statusCode), registrationName);

        // Create and add the metric to the registry
        return metricRegistry.meter(registrationName);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Do the filter chaining
        chain.doFilter(httpRequest, response);

        // Cast ServletResponse into HttpServletResponse
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Mark concerning status-code meter
        String meterName = metricsNamesByHttpStatusCode.get(Integer.valueOf(httpResponse.getStatus()));
        if (meterName == null) {
            LOGGER.warn("HTTP response status code " + httpResponse.getStatus() + " not monitored");
        } else {
            Metric metric = metricRegistry.getMeters().get(meterName);
            if (metric == null) {
                LOGGER.error("Metric not found for name " + meterName);
            } else {
                try {
                    Meter meter = (Meter) metric;
                    meter.mark();
                } catch (ClassCastException cce) {
                    LOGGER.error("Can not catch Metric into Meter");
                }
            }
        }
    }

    @Override
    public void destroy() {
        // Do nothing
    }

}
