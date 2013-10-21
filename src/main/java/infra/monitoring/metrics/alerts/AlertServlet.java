package infra.monitoring.metrics.alerts;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(loadOnStartup = 3, urlPatterns = "/alerts/*")
public class AlertServlet extends HttpServlet {

    @WebListener
    public static class ContextListener implements ServletContextListener {

        public static final String ALERTS_REGISTRY = AlertServlet.class.getCanonicalName() + ".registry";
        public static final AlertRegistry ALERT_REGISTRY = new AlertRegistry();

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            ServletContext context = sce.getServletContext();
            context.setAttribute(ALERTS_REGISTRY, ALERT_REGISTRY);
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            // Do nothing
        }

    }
    
    private transient MetricRegistry metricRegistry;
    private transient AlertRegistry alertRegistry;
    private transient ObjectMapper mapper;
    private transient AlertProcessor processor;

    @Override
    public void init(ServletConfig config) throws ServletException {
        final ServletContext context = config.getServletContext();

        // Retrieve AlertRegistry
        Object alertRegistryAttr = context.getAttribute(AlertServlet.ContextListener.ALERTS_REGISTRY);
        if (alertRegistryAttr instanceof AlertRegistry) {
            this.alertRegistry = (AlertRegistry) alertRegistryAttr;
            registerAlerts();
        } else {
            throw new ServletException("Couldn't find an AlertRegistry instance.");
        }
        
        // Retrieve MetricRegistry
        Object metricRegistryAttr = context.getAttribute(MetricsServlet.METRICS_REGISTRY);
        if (metricRegistryAttr instanceof MetricRegistry) {
            this.metricRegistry = (MetricRegistry) metricRegistryAttr;
        } else {
            throw new ServletException("Couldn't find a MetricRegistry instance.");
        }
        
        this.mapper = new ObjectMapper();
        this.processor = new AlertProcessor();
    }

    private final void registerAlerts() {
        String name = "Method #findAll should take less than 50ms";
        String metric = "domain.BookmarkServiceImpl.findAll";
        alertRegistry.register(new TimerBasedAlert(name, metric, 0.002d, MetricProperty.SNAPSHOT_MEAN, MetricComparator.GREATER));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Prepare response
        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "must-revalidate,no-cache,no-store");

        // Retrieve alerts
        Collection<Alert> alerts = alertRegistry.getAlerts().values();

        // Check alerts triggering
        for (Alert alert : alerts) {
            alert.update(metricRegistry);
        }
        
        // Populate response wih triggered alerts
        final OutputStream output = resp.getOutputStream();
        try {
            mapper.writeValue(output, alerts);
        } finally {
            output.close();
        }
    }
}
