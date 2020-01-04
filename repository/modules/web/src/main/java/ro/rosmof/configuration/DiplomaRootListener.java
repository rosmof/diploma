package ro.rosmof.configuration;

import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import ro.rosmof.Application;

import javax.servlet.ServletContext;

public class DiplomaRootListener extends ContextLoaderListener {

    @Override
    protected void customizeContext(ServletContext sc, ConfigurableWebApplicationContext applicationContext) {
        super.customizeContext(sc, applicationContext);

        //setup the spring profile
        //maybe you can insert in here some kind of evaluation of the server
        //or machine that the app is running on
        applicationContext.getEnvironment().setActiveProfiles(Application.Profiles.DEVELOPER);

    }
}
