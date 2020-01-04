package ro.rosmof.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ro.rosmof.Application;
import ro.rosmof.services.DatabaseServiceInterface;
import ro.rosmof.services.DatabaseServiceProxy;

@Component
public class RootContextBeansPostProcessor implements BeanPostProcessor {

    static final Logger logger = LoggerFactory.getLogger(RootContextBeansPostProcessor.class);
    private ConfigurableEnvironment environment;

    @Autowired
    public RootContextBeansPostProcessor(ConfigurableEnvironment environment) {
        Assert.notNull(environment, "Environment should not be null");
        this.environment = environment;
    }

    /**
     * When creating a proxy always connect to the spring framework before spring
     * creates it's own.
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DatabaseServiceInterface && !bean.getClass().getSimpleName().contains("proxy")) {
            return proxyBean((DatabaseServiceInterface) bean);
        }
        return bean;
    }

    /**
     * Simply return the bean;
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * <p>
     * Creates and returns a light proxy. The {@code callback()} method is the place to
     * insert the desired {@link org.aopalliance.intercept.Interceptor}.When invoking the
     * method pay attention to the first argument, it must be on a compatible type
     * with the {@link Enhancer} method {@code setSuperClass()}
     * </p>
     *
     * <p>
     * This is an alternative to AOP, although AOP does almost the same thing.
     * </p>
     */
    private DatabaseServiceInterface proxyBean(DatabaseServiceInterface bean) {

        DatabaseServiceInterface target = !environment.getActiveProfiles()[0].contains(Application.Profiles.DEVELOPER) ?
                bean :
                new DatabaseServiceProxy(bean);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DatabaseServiceInterface.class);
        enhancer.setCallback((org.springframework.cglib.proxy.MethodInterceptor) (o, method, objects, methodProxy) -> {
            logger.info("Choosing the appropriate DatabaseService implementation depending on the environment: {} ", o.getClass());
            return method.invoke(target, objects);
        });

        return (DatabaseServiceInterface) enhancer.create();
    }
}
