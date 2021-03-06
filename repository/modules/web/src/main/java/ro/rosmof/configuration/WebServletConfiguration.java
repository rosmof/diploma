package ro.rosmof.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * This is the web configuration file for web servlet (see web.xml).
 * By default it should only scan for controllers.
 */

@Configuration
@EnableWebMvc
@ComponentScan(value = "ro.rosmof.web")
public class WebServletConfiguration implements WebMvcConfigurer {
    @Bean
    public ViewResolver webResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }


}
