package com.agile.spirit.jba.infra.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.agile.spirit.jba")
@EnableTransactionManagement
@EnableJpaRepositories("com.agile.spirit.jba")
@Profile("test")
public class TestConfig implements EnvironmentConfig {

    private static String DRIVER_CLASS_NAME = "org.h2.Driver";
    /*
     * ";DB_CLOSE_DELAY=-1" is the solution 
     */
    private static String CONNECTION_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"; 
    private static String USERNAME = "sa";
    private static String PASSWORD = "";
    private static String SQL_DIALECT = "org.hibernate.dialect.H2Dialect";
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DRIVER_CLASS_NAME);
        ds.setUrl(CONNECTION_URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        return ds;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();

        // Hibernate
        properties.put("hibernate.dialect", SQL_DIALECT);
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.use_sql_comments", "false");
        properties.put("hibernate.format_sql", "false");

        return properties;
    }

}
