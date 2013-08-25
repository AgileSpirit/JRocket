package com.agile.spirit.jba.infra.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.agile.spirit.jba")
@EnableTransactionManagement
@EnableJpaRepositories("com.agile.spirit.jba")
@Profile("test")
public class TestConfig {
    
}
