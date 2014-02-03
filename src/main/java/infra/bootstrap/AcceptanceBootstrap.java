package infra.bootstrap;

import infra.util.DataGenerator;
import org.springframework.context.annotation.Profile;

import javax.inject.Inject;
import javax.inject.Named;

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
