package persistenceSuite.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import ro.rosmof.configuration.RootContextConfiguration;

@ContextConfiguration(classes = RootContextConfiguration.class)
public class PasswordCheckClass extends PersistenceTestBase {

    public PasswordCheckClass() {
        super(PasswordCheckClass.class.getName());
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void checkPassword() {
        Environment env = applicationContext.getEnvironment();
        System.out.println("check password method called");
    }

    @Test
    public void authenticate() {
        System.out.println("authenticate");
    }
}
