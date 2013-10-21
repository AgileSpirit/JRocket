package infra.monitoring.alerts;

import java.io.Serializable;

import com.codahale.metrics.MetricRegistry;

public abstract class Alert implements Serializable {

    private final String name;
    protected final String metric;
    private boolean active = false;

    public Alert(String name, String metric) {
        this.name = name;
        this.metric = metric;
    }

    public String getName() {
        return name;
    }

    public String getMetricName() {
        return metric;
    }

    public boolean isActive() {
        return active;
    }

    public Alert update(MetricRegistry metricRegistry) {
        this.active = check(metricRegistry);
        return this;
    }
    
    public abstract boolean check(MetricRegistry metricRegistry);

}
