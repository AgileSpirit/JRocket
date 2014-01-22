package infra.monitoring.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import infra.monitoring.metrics.healthchecks.BasicHealthCheck;
import infra.monitoring.metrics.healthchecks.DatabaseHealthCheck;
import infra.monitoring.metrics.healthchecks.RestResourcesHealthCheck;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMetrics
public class MetricsConfig extends MetricsConfigurerAdapter {

  @Inject
  private Environment env;

  @PersistenceContext
  private EntityManager entityManager;

  private final MetricRegistry metricRegistry = new MetricRegistry();

  @Bean
  @Override
  public MetricRegistry getMetricRegistry() {
    return metricRegistry;
  }

  private final HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

  @Bean
  @Override
  public HealthCheckRegistry getHealthCheckRegistry() {
    return healthCheckRegistry;
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
    healthCheckRegistry.register("Metrics HealthCheck mecanism", new BasicHealthCheck());
    healthCheckRegistry.register("Database", new DatabaseHealthCheck(entityManager));
    healthCheckRegistry.register("REST resources", new RestResourcesHealthCheck(env.getProperty("checks.rest.resources.ping")));
  }

}
