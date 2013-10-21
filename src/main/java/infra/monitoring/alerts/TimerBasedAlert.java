package infra.monitoring.alerts;

import com.codahale.metrics.Timer;

public class TimerBasedAlert extends MetricBasedAlert<Timer> {

    public TimerBasedAlert(String name, String metric, double threshold, MetricProperty property, MetricComparator comparator) {
        super(name, metric, threshold, property, comparator);
    }

}
