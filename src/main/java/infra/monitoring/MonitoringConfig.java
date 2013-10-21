package infra.monitoring;

import infra.monitoring.metrics.BasicHealthCheck;
import infra.monitoring.metrics.DatabaseHealthCheck;
import infra.monitoring.metrics.HealthCheckServletContextListener;
import infra.monitoring.metrics.MetricsServletContextListener;
import infra.monitoring.metrics.RestResourcesHealthCheck;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

@Configuration
@EnableMetrics
public class MonitoringConfig extends MetricsConfigurerAdapter {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MetricRegistry getMetricRegistry() {
        return MetricsServletContextListener.METRIC_REGISTRY;
    }

    @Override
    public HealthCheckRegistry getHealthCheckRegistry() {
        return HealthCheckServletContextListener.HEALTH_CHECK_REGISTRY;
    }

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        // Console reporter
        ConsoleReporter.forRegistry(metricRegistry).build().start(5, TimeUnit.MINUTES);

        // SLF4J reporter
        Slf4jReporter.forRegistry(metricRegistry).outputTo(LoggerFactory.getLogger(getClass().getCanonicalName()))
                .convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build().start(5, TimeUnit.MINUTES);

        // JMX reporter
        JmxReporter.forRegistry(metricRegistry).build().start();
    }

    @PostConstruct
    private void registerHealthChecks() {
        // Register HealthChecks
        final HealthCheckRegistry registry = HealthCheckServletContextListener.HEALTH_CHECK_REGISTRY;
        final String className = getClass().getCanonicalName();

        registry.register(className + "basic", new BasicHealthCheck());
        registry.register(className + "restServicesAvailability", new RestResourcesHealthCheck());
        registry.register(className + "databaseAvailability", new DatabaseHealthCheck(entityManager));
    }

}
