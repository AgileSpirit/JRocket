package infra.monitoring.metrics.alerts;

public enum MetricComparator {

    EQUALS {
        @Override
        public boolean compare(double leftOperand, double rightOperand) {
            return leftOperand == rightOperand;
        }
    }, //
    GREATER {
        @Override
        public boolean compare(double leftOperand, double rightOperand) {
            return leftOperand > rightOperand;
        }
    }, //
    GREATER_OR_EQUALS {
        @Override
        public boolean compare(double leftOperand, double rightOperand) {
            return leftOperand >= rightOperand;
        }
    }, //
    LESS {
        @Override
        public boolean compare(double leftOperand, double rightOperand) {
            return leftOperand < rightOperand;
        }
    }, //
    LESS_OR_EQUALS {
        @Override
        public boolean compare(double leftOperand, double rightOperand) {
            return leftOperand <= rightOperand;
        }
    };

    public abstract boolean compare(double leftOperand, double rightOperand);
}
