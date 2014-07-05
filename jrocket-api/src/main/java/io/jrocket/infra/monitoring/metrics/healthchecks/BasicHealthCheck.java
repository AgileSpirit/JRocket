package io.jrocket.infra.monitoring.metrics.healthchecks;

import com.codahale.metrics.health.HealthCheck;

/**
 * Example of basic HealthCheck that always returns true
 */
public class BasicHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy("Metrics HealthCheck mecanism are OK");
    }

}
