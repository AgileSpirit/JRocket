package com.agile.spirit.jba.infra.bootstrap;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Profile;

import com.agile.spirit.jba.infra.util.DataGenerator;

@Named
@Profile("production")
public class ProductionBootstrap extends ApplicationBootstrap {

    @Inject 
    private DataGenerator dataGenerator; 
    
    @Override
    void bootstrap() {
        dataGenerator.populateData();
        dataGenerator.retrieveAndDisplayAllData();
    }
}
