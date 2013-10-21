package infra.monitoring.metrics.alerts;

import com.codahale.metrics.Meter;

public class MeterBasedAlert extends MetricBasedAlert<Meter> {

    public MeterBasedAlert(String name, String metric, double threshold, MetricProperty property, MetricComparator comparator) {
        super(name, metric, threshold, property, comparator);
    }

}
