package infra.monitoring.alerts;

public enum MetricProperty {
    COUNT("count") {
        @Override
        public double convert(double value) {
            return value;
        }
    }, //
    MEAN_RATE("meanRate") {
        @Override
        public double convert(double value) {
            return value / (C3 / C0);
        }
    }, //
    M1_RATE("oneMinuteRate") {
        @Override
        public double convert(double value) {
            return value / (C3 / C0);
        }
    }, //
    M5_RATE("fiveMinuteRate") {
        @Override
        public double convert(double value) {
            return value / (C3 / C0);
        }
    }, //
    M15_RATE("fifteenMinuteRate") {
        @Override
        public double convert(double value) {
            return value / (C3 / C0);
        }
    }, //
    SNAPSHOT_MEAN("snapshot.mean") {
        @Override
        public double convert(double value) {
            return value / (C3 / C0);
        }
    }, //
    SNAPSHOT_MIN("snapshot.min") {
        @Override
        public double convert(double value) {
            return ((long) value) / (C3 / C0);
        }
    }, //
    SNAPSHOT_MAX("snapshot.max") {
        @Override
        public double convert(double value) {
            return ((long) value) / (C3 / C0);
        }
    }, //
    SNAPSHOT_P50("50thPercentile") {
        @Override
        public double convert(double value) {
            return value / (C3 / C0);
        }
    }, //
    SNAPSHOT_P75("75thPercentile") {
        @Override
        public double convert(double value) {
            return value / (C3 / C0);
        }
    }, //
    SNAPSHOT_P99("99thPercentile") {
        @Override
        public double convert(double value) {
            return value / (C3 / C0);
        }
    };

    // Handy constants for conversion methods
    static final double C0 = 1D;
    static final double C1 = C0 * 1000D;
    static final double C2 = C1 * 1000D;
    static final double C3 = C2 * 1000D;
    static final double C4 = C3 * 60D;
    static final double C5 = C4 * 60D;
    static final double C6 = C5 * 24D;

    private final String property;

    MetricProperty(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public abstract double convert(double value);
}
