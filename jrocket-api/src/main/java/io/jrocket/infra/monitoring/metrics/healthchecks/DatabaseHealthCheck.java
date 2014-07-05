package io.jrocket.infra.monitoring.metrics.healthchecks;

import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class DatabaseHealthCheck extends HealthCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHealthCheck.class);

    private final EntityManager entityManager;

    public DatabaseHealthCheck(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Result check() throws Exception {
        LOGGER.info("Do DatabaseHealthCheck...");
        Result result;
        try {
            Object data = entityManager.createQuery("FROM Bookmark").getFirstResult();
            if (data == null) {
                LOGGER.warn("Check is KO");
                result = Result.unhealthy("Request returned no result");
            } else {
                LOGGER.info("Check is OK");
                result = Result.healthy("Connection to the database is OK");
            }
        } catch (Exception e) {
            LOGGER.error("Check is KO : an error occured");
            LOGGER.error(e.toString());
            result = Result.unhealthy("Request returned no result");
        }
        return result;
    }

}
