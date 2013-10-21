package infra.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:environment/application-localhost.properties")
@Profile("localhost")
public class LocalhostConfig implements EnvironmentConfig {

    @Inject
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("hibernate.connection.driver_class"));
        ds.setUrl(env.getProperty("hibernate.connection.url"));
        ds.setUsername(env.getProperty("hibernate.connection.username"));
        ds.setPassword(env.getProperty("hibernate.connection.password"));
        return ds;
    }

}
