package persistenceSuite.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import ro.rosmof.configuration.RootContextConfiguration;
import ro.rosmof.model.entities.User;
import ro.rosmof.services.ErrorService;
import ro.rosmof.services.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Random;

@ContextConfiguration(classes = RootContextConfiguration.class)
@Transactional
public class UserTestClass extends PersistenceTestBase {

    @Autowired
    private UserService userService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private ApplicationContext applicationContext;

    @PersistenceContext
    private EntityManager entityManager;


    public UserTestClass() {
        super(UserTestClass.class.getName());
    }

    @Test
    @Rollback(false)
    public void checkUser() {

        try {
            User user = null;
            for (int i = 0; i < 10; i++) {
                user = new User();
                user.setUsername("alexandru " + new Random().nextInt());
                user.setPassword(" ----- ");
                userService.saveUser(user);
            }

            userService.saveUserWithException(user);
        } catch (Exception e) {
            errorService.saveErrorWithNewTransaction(e);
        }
    }
}
