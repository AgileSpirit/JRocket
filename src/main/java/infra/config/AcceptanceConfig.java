package infra.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:environment/application-acceptance.properties")
@Profile("acceptance")
public class AcceptanceConfig implements EnvironmentConfig {

    @Bean
    public DataSource dataSource() {
        return new ComboPooledDataSource();
    }

}
