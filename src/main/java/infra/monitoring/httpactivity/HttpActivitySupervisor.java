package infra.monitoring.httpactivity;

import infra.monitoring.metrics.MetricsServletContextListener;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import org.springframework.scheduling.annotation.Scheduled;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.SlidingTimeWindowReservoir;

/**
 * Main class for monitoring Web performance
 * 
 */
@Named
public class HttpActivitySupervisor {

    // HTTP activity
    private final Histogram histogram;
    private final SlidingTimeWindowReservoir reservoir;

    public HttpActivitySupervisor() {
        this.reservoir = new SlidingTimeWindowReservoir(1, TimeUnit.MINUTES);
        this.histogram = new Histogram(reservoir);
        MetricsServletContextListener.METRIC_REGISTRY.register(getClass().getCanonicalName() + ".HttpActivity", histogram);
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void simulateHttpActivity() {
        // Generate a random value
        int lower = 1;
        int higher = 1000;

        int randomValue = (int)(Math.random() * (higher-lower)) + lower;
        
        // Mark
        histogram.update(randomValue);
    }

}
