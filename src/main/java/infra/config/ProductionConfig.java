package infra.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:environment/application-production.properties")
@Profile("production")
public class ProductionConfig implements EnvironmentConfig {

    @Bean
    public DataSource dataSource() {
        return new ComboPooledDataSource();
    }

}
