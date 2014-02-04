package io.jrocket.infra.bootstrap;

import io.jrocket.infra.util.DataGenerator;
import org.springframework.context.annotation.Profile;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Profile("integration")
public class IntegrationBootstrap extends ApplicationBootstrap {

    @Inject
    private DataGenerator dataGenerator;

    @Override
    void bootstrap() {
        dataGenerator.retrieveAndDisplaySortedData();
    }

}
