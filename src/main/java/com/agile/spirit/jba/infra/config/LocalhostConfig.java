package com.agile.spirit.jba.infra.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:environment/application-localhost.properties")
@Profile("localhost")
public class LocalhostConfig implements EnvironmentConfig {

    /*
     * JDBC properties
     */
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /*
     * Hibernate properties
     */

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Value("${hibernate.generate_statistics}")
    private String hibernateGenerateStatistics;

    @Value("${hibernate.archive.autodetection}")
    private String hibernateArchiveAutodetection;

    @Value("${hibernate.use_sql_comments}")
    private String hibernateUseSqlComments;

    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();

        // Hibernate
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.put("hibernate.generate_statistics", hibernateGenerateStatistics);
        properties.put("hibernate.archive.autodetection", hibernateArchiveAutodetection);
        properties.put("hibernate.use_sql_comments", hibernateUseSqlComments);
        properties.put("hibernate.format_sql", hibernateFormatSql);

        return properties;
    }

}
