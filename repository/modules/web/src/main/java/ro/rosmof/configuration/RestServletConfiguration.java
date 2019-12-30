package ro.rosmof.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestServletConfiguration {
    @Bean
    public String testing() {
        return "adad";
    }
}
