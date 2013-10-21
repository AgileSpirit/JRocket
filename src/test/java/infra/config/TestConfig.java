package infra.config;

import infra.repository.RepositoryConfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

@Configuration
@ComponentScan(".")
@EnableTransactionManagement
@EnableJpaRepositories("")
@Profile("test")
@Import({RepositoryConfig.class})
public class TestConfig implements EnvironmentConfig {

    /*
     * ================
     * DATA PERSISTENCE
     * ================
     */

    private static String DRIVER_CLASS_NAME = "org.h2.Driver";
    /*
     * Without the URL option 'DB_CLOSE_DELAY=-1', the H2 in-memory database closes just after Hibernate schema initialization.
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

    /*
     * Required to fix DBUnit warning about DefaultDataTypeFactory : 
     * http://dbunit.sourceforge.net/faq.html#DefaultDataTypeFactory
     */
    @Bean
    public IDatabaseConnection dbUnitDatabaseConnection() throws Exception {
        DatabaseConfigBean databaseConfig = new DatabaseConfigBean();
        databaseConfig.setDatatypeFactory(new H2DataTypeFactory());

        DatabaseDataSourceConnectionFactoryBean databaseConnectionFactory = new DatabaseDataSourceConnectionFactoryBean(dataSource());
        databaseConnectionFactory.setDatabaseConfig(databaseConfig);
        return databaseConnectionFactory.getObject();
    }

    /*
     * =======
     * MAILING
     * =======
     */

    @Bean
    public Properties javaMailProperties() {
        Properties properties = new Properties();
        return properties;
    }

}
