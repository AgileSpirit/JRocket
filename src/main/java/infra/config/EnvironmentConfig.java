package infra.config;

import javax.sql.DataSource;

public interface EnvironmentConfig {

    DataSource dataSource();

}
