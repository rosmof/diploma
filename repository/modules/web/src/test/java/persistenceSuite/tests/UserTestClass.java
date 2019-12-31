package persistenceSuite.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ro.rosmof.configuration.RootContextConfiguration;
import ro.rosmof.model.entities.User;
import ro.rosmof.model.repositories.UserRepository;

@ContextConfiguration(classes = RootContextConfiguration.class)
@Transactional(value = "transactionManager")
public class UserTestClass extends PersistenceTestBase {

    @Autowired
    private UserRepository userRepository;


    public UserTestClass() {
        super(UserTestClass.class.getName());
    }

    @Test
    @Rollback(false)
    public void checkUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("alexandru");
        user.setPassword("123456");

        userRepository.save(user);

    }
}
