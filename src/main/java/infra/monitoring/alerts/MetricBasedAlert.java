package infra.monitoring.alerts;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;

public abstract class MetricBasedAlert<T extends Metric> extends Alert {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricBasedAlert.class);

    protected final double threshold;
    protected final MetricProperty property;
    protected final MetricComparator comparator;

    public MetricBasedAlert(String name, String metric, double threshold, MetricProperty property, MetricComparator comparator) {
        super(name, metric);
        this.threshold = threshold;
        this.property = property;
        this.comparator = comparator;
    }

    @Override
    public final boolean check(MetricRegistry registry) {
        T metric = getMetric(registry);
        if (metric == null) {
            return false;
        }
        return isActive(metric, registry);
    }

    @SuppressWarnings("unchecked")
    protected final T getMetric(MetricRegistry registry) {
        Metric object = registry.getMetrics().get(metric);
        if (object == null) {
            LOGGER.warn("Metric " + metric + "not found in MetricRegistry");
        } else {
            try {
                return (T) object;
            } catch (ClassCastException cce) {
                LOGGER.warn("Metric can not be cast into concrete class");
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comparator == null) ? 0 : comparator.hashCode());
        result = prime * result + ((property == null) ? 0 : property.hashCode());
        long temp;
        temp = Double.doubleToLongBits(threshold);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MetricBasedAlert other = (MetricBasedAlert) obj;
        if (comparator != other.comparator)
            return false;
        if (property == null) {
            if (other.property != null)
                return false;
        } else if (!property.equals(other.property))
            return false;
        if (Double.doubleToLongBits(threshold) != Double.doubleToLongBits(other.threshold))
            return false;
        return true;
    }

    private final boolean isActive(T metric, MetricRegistry registry) {
        try {
            double metricValue = getMetricValue(registry);
            return comparator.compare(metricValue, threshold);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the property value of the metric.
     * 
     * @return
     */
    protected double getMetricValue(MetricRegistry registry) throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        T metric = getMetric(registry);
        Object value = PropertyUtils.getProperty(metric, property.getProperty());
        if (value instanceof Double) {
            return property.convert((Double)value);
        }
        if (value instanceof Long) {
            return property.convert(((Long)value).doubleValue());
        }
        return 0;
    }

}
