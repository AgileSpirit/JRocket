package io.jrocket.infra.bootstrap;

import io.jrocket.infra.util.DataGenerator;
import org.springframework.context.annotation.Profile;

import javax.inject.Inject;
import javax.inject.Named;

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
