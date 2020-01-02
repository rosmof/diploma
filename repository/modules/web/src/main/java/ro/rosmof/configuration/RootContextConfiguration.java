package ro.rosmof.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ro.rosmof.model.configuration.ModelConfiguration;
import ro.rosmof.services.configuration.ServiceConfiguration;

@Configuration
@Import({ModelConfiguration.class, ServiceConfiguration.class})
public class RootContextConfiguration {

    @Bean
    public String description() {
        return "adasd";
    }
}
