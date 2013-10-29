package infra.monitoring.metrics;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;

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
