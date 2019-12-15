package ro.rosmof.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ro.rosmof.services.FirstServiceInterface;

@Configuration
@ComponentScan("ro.rosmof.services")
public class RootContextConfiguration {

    //check DI
    @Autowired
    public RootContextConfiguration(FirstServiceInterface firstService) {

    }


}
