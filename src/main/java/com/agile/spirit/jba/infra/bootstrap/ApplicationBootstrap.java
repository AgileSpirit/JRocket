package com.agile.spirit.jba.infra.bootstrap;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@Named
public abstract class ApplicationBootstrap {

    private static Logger logger = LoggerFactory.getLogger(ApplicationBootstrap.class);

    @Value("${environment}") 
    private String environment;

    @PostConstruct
    public void doBootstrap() {
        bootstrap();
        logger.info("Application is ready for environment '" + environment + "' !");
    }
    
    abstract void bootstrap();


}
