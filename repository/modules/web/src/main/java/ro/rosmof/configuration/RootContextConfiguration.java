package ro.rosmof.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import ro.rosmof.model.configuration.ModelConfiguration;
import ro.rosmof.services.configuration.ServiceConfiguration;

@Configuration
@Import({ModelConfiguration.class, ServiceConfiguration.class, RootContextBeansPostProcessor.class})
public class RootContextConfiguration {

    private final ConfigurableEnvironment environment;

    public RootContextConfiguration(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Bean
    public String description() {
        return "adasd";
    }
}
