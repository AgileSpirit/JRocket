package infra.monitoring.servletfilter;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NOT_MODIFIED;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import infra.monitoring.metrics.MetricsServletContextListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.Meter;
import com.codahale.metrics.Metric;

public class HttpActivityServletFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpActivityServletFilter.class);
    
    private final Map<Integer, String> metricsNamesByHttpStatusCode = new HashMap<Integer, String>();
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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
        return MetricsServletContextListener.METRIC_REGISTRY.meter(registrationName);
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
            Metric metric = MetricsServletContextListener.METRIC_REGISTRY.getMeters().get(meterName);
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
