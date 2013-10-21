package infra.monitoring.metrics.alerts;

import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.common.collect.ImmutableList;

@Named
public class AlertSupervisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertSupervisor.class);
    
    // Alerts
    private final transient AlertProcessor alertProcessor;

    public AlertSupervisor() {
        this.alertProcessor = new AlertProcessor();
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void monitorAlerts() {
        List<Alert> alerts = ImmutableList.copyOf(AlertServlet.ContextListener.ALERT_REGISTRY.getAlerts().values());
        List<Alert> triggeredAlerts = alertProcessor.checkAlerts(alerts);
        if (!triggeredAlerts.isEmpty()) {
            LOGGER.warn("An alert was triggered");
        }
    }

}
