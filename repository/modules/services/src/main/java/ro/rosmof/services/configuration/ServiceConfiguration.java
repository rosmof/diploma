package ro.rosmof.services.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ro.rosmof.services.DatabaseService;
import ro.rosmof.services.DatabaseServiceInterface;

@Configuration
@ComponentScan(basePackages = {"ro.rosmof.services"})
public class ServiceConfiguration {

    @Bean
    public DatabaseServiceInterface databaseService() {
        return new DatabaseService();
    }
}
