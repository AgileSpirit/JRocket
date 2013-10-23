package infra.monitoring.metrics;

import static infra.monitoring.metrics.HealthCheckServletContextListener.HEALTH_CHECK_REGISTRY;
import infra.monitoring.metrics.healthchecks.BasicHealthCheck;
import infra.monitoring.metrics.healthchecks.DatabaseHealthCheck;
import infra.monitoring.metrics.healthchecks.RestResourcesHealthCheck;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

@Configuration
@EnableMetrics
public class MetricsConfig extends MetricsConfigurerAdapter {

    @Inject
    private Environment env;
    
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
        ConsoleReporter.forRegistry(metricRegistry).build().start(1, TimeUnit.HOURS);

        // SLF4J reporter
        Slf4jReporter.forRegistry(metricRegistry).outputTo(LoggerFactory.getLogger(getClass().getCanonicalName()))
                .convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build().start(1, TimeUnit.HOURS);

        // JMX reporter
        JmxReporter.forRegistry(metricRegistry).build().start();
    }

    @PostConstruct
    private void registerHealthChecks() {
        HEALTH_CHECK_REGISTRY.register("Metrics HealthCheck mecanism", new BasicHealthCheck());
        HEALTH_CHECK_REGISTRY.register("Database", new DatabaseHealthCheck(entityManager));
        HEALTH_CHECK_REGISTRY.register("REST resources", new RestResourcesHealthCheck(env.getProperty("checks.rest.resources.ping")));
    }
    
}
