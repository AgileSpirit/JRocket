package infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.naming.MetadataNamingStrategy;

@Configuration
@ComponentScan(".")
@EnableMBeanExport
public class ApplicationConfig  {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /*
     * JMX MBean configuration
     */
    
    @Bean
    public MetadataNamingStrategy namingStrategy() {
        return new MetadataNamingStrategy(new AnnotationJmxAttributeSource());
    }
    
}
