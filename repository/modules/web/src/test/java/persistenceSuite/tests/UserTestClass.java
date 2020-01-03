package persistenceSuite.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ro.rosmof.configuration.RootContextConfiguration;
import ro.rosmof.model.entities.User;
import ro.rosmof.services.ErrorService;
import ro.rosmof.services.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Random;

@ContextConfiguration(classes = RootContextConfiguration.class)
//@Transactional
public class UserTestClass extends PersistenceTestBase {

    @Autowired
    private UserService userService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private ApplicationContext applicationContext;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public UserTestClass() {
        super(UserTestClass.class.getName());
    }

    @BeforeTransaction
    public void setupTransaction() {
        logger.info("Setting up transaction for test");
    }

    @AfterTransaction
    public void clearTransaction() {
        logger.info("Clear transaction after test");
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

            Connection connection = DataSourceUtils.getConnection(dataSource);


            System.out.println("fa ceva cu connection");


            //userService.saveUserWithException(user);
        } catch (Exception e) {
            errorService.saveErrorWithNewTransaction(e);
        }
    }
}
