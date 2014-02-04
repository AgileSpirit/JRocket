package io.jrocket.infra.monitoring.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

public class MetricsServletContextListener extends MetricsServlet.ContextListener {

    private MetricRegistry metricRegistry;

    @Override
    protected MetricRegistry getMetricRegistry() {
        return this.metricRegistry;
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        this.metricRegistry = springContext.getBean(MetricRegistry.class);

        super.contextInitialized(event);
    }

}
