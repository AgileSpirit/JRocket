package infra.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static infra.util.PropertyHelper.setProperty;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("infra.repository")
public class RepositoryConfig {

    @Inject
    private Environment env;

    @Inject
    private DataSource dataSource;

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();

        // JDBC connection
        setProperty(env, properties, "hibernate.connection.driver_class");
        setProperty(env, properties, "hibernate.connection.url");
        setProperty(env, properties, "hibernate.connection.username");
        setProperty(env, properties, "hibernate.connection.password");

        // Hibernate properties
        setProperty(env, properties, "hibernate.dialect");
        setProperty(env, properties, "hibernate.show_sql");
        setProperty(env, properties, "hibernate.hbm2ddl.auto");
        setProperty(env, properties, "hibernate.generate_statistics");
        setProperty(env, properties, "hibernate.archive.autodetection");
        setProperty(env, properties, "hibernate.use_sql_comments");
        setProperty(env, properties, "hibernate.format_sql");

        // C3P0 connection pooling
        setProperty(env, properties, "hibernate.connection.provider_class");
        setProperty(env, properties, "hibernate.c3p0.min_size");
        setProperty(env, properties, "hibernate.c3p0.max_size");
        setProperty(env, properties, "hibernate.c3p0.acquire_increment");
        setProperty(env, properties, "hibernate.c3p0.idle_test_period");
        setProperty(env, properties, "hibernate.c3p0.max_statements");
        setProperty(env, properties, "hibernate.c3p0.timeout");

        return properties;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setDataSource(dataSource);
        factory.setJpaProperties(jpaProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

}
