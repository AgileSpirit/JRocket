package com.agile.spirit.jba.infra.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:environment/application-integration.properties")
@Profile("integration")
public class IntegrationConfig implements EnvironmentConfig {

    /*
     * ================
     * DATA PERSISTENCE
     * ================
     */
    
    /*
     * Hibernate properties
     */

    @Value("${hibernate.connection.driver_class}")
    private String hibernateConnectionDriverClass;

    @Value("${hibernate.connection.url}")
    private String hibernateConnectionUrl;

    @Value("${hibernate.connection.username}")
    private String hibernateConnectionUsername;

    @Value("${hibernate.connection.password}")
    private String hibernateConnectionPassword;

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

    /*
     * C3P0 (JDBC connection pooling) properties
     */
    @Value("${hibernate.connection.provider_class}")
    private String hibernateConnectionProviderClass;

    @Value("${hibernate.c3p0.min_size}")
    private String hibernateC3P0MinSize;

    @Value("${hibernate.c3p0.max_size}")
    private String hibernateC3P0MaxSize;

    @Value("${hibernate.c3p0.acquire_increment}")
    private String hibernateC3P0AcquireIncrement;

    @Value("${hibernate.c3p0.idle_test_period}")
    private String hibernateC3P0IdleTestPeriod;

    @Value("${hibernate.c3p0.max_statements}")
    private String hibernateC3P0MaxStatements;

    @Value("${hibernate.c3p0.timeout}")
    private String hibernateC3P0Timeout;
    
    @Bean
    public DataSource dataSource() {
        return new ComboPooledDataSource();
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();

        // JDBC connection
        properties.put("hibernate.connection.driver_class", hibernateConnectionDriverClass);
        properties.put("hibernate.connection.url", hibernateConnectionUrl);
        properties.put("hibernate.connection.username", hibernateConnectionUsername);
        properties.put("hibernate.connection.password", hibernateConnectionPassword);

        // Hibernate properties
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.put("hibernate.generate_statistics", hibernateGenerateStatistics);
        properties.put("hibernate.archive.autodetection", hibernateArchiveAutodetection);
        properties.put("hibernate.use_sql_comments", hibernateUseSqlComments);
        properties.put("hibernate.format_sql", hibernateFormatSql);

        // C3P0 connection pooling
        properties.put("hibernate.connection.provider_class", hibernateConnectionProviderClass);
        properties.put("hibernate.c3p0.min_size", hibernateC3P0MinSize);
        properties.put("hibernate.c3p0.max_size", hibernateC3P0MaxSize);
        properties.put("hibernate.c3p0.acquire_increment", hibernateC3P0AcquireIncrement);
        properties.put("hibernate.c3p0.idle_test_period", hibernateC3P0IdleTestPeriod);
        properties.put("hibernate.c3p0.max_statements", hibernateC3P0MaxStatements);
        properties.put("hibernate.c3p0.timeout", hibernateC3P0Timeout);
        return properties;
    }

    /*
     * =======
     * MAILING
     * =======
     */
    
    @Value("${mail.debug}")
    private String mailDebug;

    @Value("${mail.username}")
    private String mailUsername;
    
    @Value("${mail.password}")
    private String mailPassword;
    
    @Value("${mail.smtp.host}")
    private String mailSmtpHost;
    
    @Value("${mail.smtp.port}")
    private String mailSmtpPort;
    
    @Value("${mail.smtp.auth}")
    private String mailSmtpAuth;
    
    @Value("${mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;
    
    @Value("${mail.template.from}")
    private String mailTemplateFrom;
    
    @Bean
    public Properties javaMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.debug", mailDebug);
        properties.put("mail.username", mailUsername);
        properties.put("mail.password", mailPassword);
        properties.put("mail.smtp.host", mailSmtpAuth);
        properties.put("mail.smtp.port", mailSmtpPort);
        properties.put("mail.smtp.auth", mailSmtpAuth);
        properties.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        properties.put("mail.template.from", mailTemplateFrom);
        return properties;
    }

}
