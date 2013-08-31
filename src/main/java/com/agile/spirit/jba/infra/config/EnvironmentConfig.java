package com.agile.spirit.jba.infra.config;

import javax.sql.DataSource;

public interface EnvironmentConfig {

    DataSource dataSource();

}
