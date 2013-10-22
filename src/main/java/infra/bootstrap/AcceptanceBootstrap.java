package infra.bootstrap;

import infra.util.DataGenerator;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Profile;

@Named
@Profile("acceptance")
public class AcceptanceBootstrap extends ApplicationBootstrap {

    @Inject
    private DataGenerator dataGenerator;

    @Override
    void bootstrap() {
        dataGenerator.populateData();
    }
}
