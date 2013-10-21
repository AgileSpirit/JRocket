package infra.monitoring.metrics.alerts;

import infra.monitoring.metrics.MetricsServletContextListener;

import java.util.ArrayList;
import java.util.List;

import com.codahale.metrics.MetricRegistry;

public class AlertProcessor {

    private final transient MetricRegistry metrics = MetricsServletContextListener.METRIC_REGISTRY;

    public List<Alert> checkAlerts(List<Alert> alerts) {

        final List<Alert> activatedAlerts = new ArrayList<>();

        for (Alert alert : alerts) {
            alert.update(metrics);
        }
        return activatedAlerts;

    }

}
