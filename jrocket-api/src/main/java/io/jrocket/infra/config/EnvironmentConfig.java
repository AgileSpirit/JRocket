package io.jrocket.infra.config;

import javax.sql.DataSource;

public interface EnvironmentConfig {

    DataSource dataSource();

}
