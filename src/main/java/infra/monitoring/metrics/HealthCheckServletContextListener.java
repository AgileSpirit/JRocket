package infra.monitoring.metrics;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

public class HealthCheckServletContextListener extends HealthCheckServlet.ContextListener {

    private HealthCheckRegistry healthCheckRegistry;

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return this.healthCheckRegistry;
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        this.healthCheckRegistry = springContext.getBean(HealthCheckRegistry.class);

        super.contextInitialized(event);
    }
}
