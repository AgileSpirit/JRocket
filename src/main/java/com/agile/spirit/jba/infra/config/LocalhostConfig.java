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

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(hibernateConnectionDriverClass);
        ds.setUrl(hibernateConnectionUrl);
        ds.setUsername(hibernateConnectionUsername);
        ds.setPassword(hibernateConnectionPassword);
        return ds;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();

        // JDBC connection
        properties.put("hibernate.connection.driver_class", hibernateConnectionDriverClass);
        properties.put("hibernate.connection.url", hibernateConnectionUrl);
        properties.put("hibernate.connection.username", hibernateConnectionUsername);
        properties.put("hibernate.connection.password", hibernateConnectionPassword);

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

    /*
     * =======
     * MAILING
     * =======
     */

    @Value("${mail.debug}")
    private String mailDebug;

    @Value("${mail.smtp.host}")
    private String mailSmtpHost;
    
    @Value("${mail.smtp.port}")
    private String mailSmtpPort;
    
    @Value("${mail.smtp.auth}")
    private String mailSmtpAuth;
    
    @Value("${mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;
    
    @Bean
    public Properties javaMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.debug", mailDebug);
        properties.put("mail.smtp.host", mailSmtpHost);
        properties.put("mail.smtp.port", mailSmtpPort);
        properties.put("mail.smtp.auth", mailSmtpAuth);
        properties.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        return properties;
    }

}
