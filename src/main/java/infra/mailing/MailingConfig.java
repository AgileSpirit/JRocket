package infra.mailing;

import static infra.util.PropertyHelper.setProperty;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

@Configuration
public class MailingConfig {

    @Inject
    private Environment env;

    @Bean
    public Properties javaMailProperties() {
        Properties properties = new Properties();
        setProperty(env, properties, "mail.debug");
        setProperty(env, properties, "mail.smtp.host");
        setProperty(env, properties, "mail.smtp.port");
        setProperty(env, properties, "mail.smtp.auth");
        setProperty(env, properties, "mail.smtp.starttls.enable");
        return properties;
    }

    @Bean
    public VelocityEngine velocityEngine() throws VelocityException, IOException {
        VelocityEngineFactoryBean factoryBean = new VelocityEngineFactoryBean();
        factoryBean.setResourceLoaderPath("classpath:velocity");

        VelocityEngine velocityEngine = factoryBean.createVelocityEngine();
        return velocityEngine;
    }

    /*
     * Notice : setting username and password is required because, when sending
     * message, JavaMailSenderImpl does not look up in the javaMailProperties
     * (cf. JavaMailSenderImpl#l.389, #getUsername() & #getPassword()).
     */
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(env.getProperty("mail.username"));
        mailSender.setPassword(env.getProperty("mail.password"));
        mailSender.setJavaMailProperties(javaMailProperties());
        return mailSender;
    }

}
