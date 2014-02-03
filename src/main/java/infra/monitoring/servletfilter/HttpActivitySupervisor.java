package infra.monitoring.servletfilter;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SlidingTimeWindowReservoir;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * Main class for monitoring Web performance
 */
public class HttpActivitySupervisor {

    // HTTP activity
    private final Histogram histogram;
    private final SlidingTimeWindowReservoir reservoir;

    @Inject
    private MetricRegistry metricRegistry;

    public HttpActivitySupervisor() {
        this.reservoir = new SlidingTimeWindowReservoir(1, TimeUnit.MINUTES);
        this.histogram = new Histogram(reservoir);
        metricRegistry.register(getClass().getCanonicalName() + ".HttpActivity", histogram);
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void simulateHttpActivity() {
        // Generate a random value
        int lower = 1;
        int higher = 1000;

        int randomValue = (int) (Math.random() * (higher - lower)) + lower;

        // Mark
        histogram.update(randomValue);
    }

}
