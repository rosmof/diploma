package ro.rosmof.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ro.rosmof.model.configuration.ModelConfiguration;
import ro.rosmof.services.FirstServiceInterface;
import ro.rosmof.services.configuration.ServiceConfiguration;

@Configuration
@Import({ModelConfiguration.class, ServiceConfiguration.class})
public class RootContextConfiguration {

    //check DI
    @Autowired
    public RootContextConfiguration(FirstServiceInterface firstService) {

    }

}
