package com.agile.spirit.jba.infra.config;

import java.util.Properties;

import javax.sql.DataSource;

public interface EnvironmentConfig {
    
    DataSource dataSource();
    
    Properties jpaProperties();
    
    Properties javaMailProperties();
}
