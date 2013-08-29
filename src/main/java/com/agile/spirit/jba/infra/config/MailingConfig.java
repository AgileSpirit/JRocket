package com.agile.spirit.jba.infra.config;

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
    
    @Inject
    private Properties javaMailProperties;

    @Bean
    public VelocityEngine velocityEngine() throws VelocityException, IOException {
        VelocityEngineFactoryBean factoryBean = new VelocityEngineFactoryBean();
        factoryBean.setResourceLoaderPath("classpath:velocity/templates");

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
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
    
}
