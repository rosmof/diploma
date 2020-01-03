package ro.rosmof.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.rosmof.model.annotations.DiplomaTransactionDefault;
import ro.rosmof.model.entities.User;
import ro.rosmof.model.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@DiplomaTransactionDefault
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private ErrorService errorService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserRepository repository, ErrorService errorService) {
        this.userRepository = repository;
        this.errorService = errorService;
    }

    @Override
    public void saveUserWithException(User add) {
        try {
            Optional<User> existingUser = userRepository.findById(120L);
            if (existingUser.isPresent()) {
                User user = existingUser.get();
                user.setPassword("888---");
                userRepository.save(user);
            }

            List<User> userList = userRepository.findAll();
            for (User user : userList) {
                if (Increment.by1() == 2) {
                    throw new ServiceException("deja am facut asta de 2 ori");
                }

                user.setPassword("****");
                user.setUsername(" ---- ");
                System.out.println("----------------------");
                userRepository.save(user);
            }
        } catch (Exception e) {
            logger.info("Exception at service second level - XXXX");
            errorService.saveErrorWithNewTransaction(e);
        }
    }

    @Override
    public void saveUserLists(List<User> list) {
        try {
            list.forEach(u -> {
                userRepository.save(u);
            });

            saveUserWithException(new User());
        } catch (Exception e) {
            logger.info("Exception on first service level - XXXX");
            errorService.saveErrorWithNewTransaction(e);
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            userRepository.save(user);
            logger.info(String.format("User %s saved successfully!", user.getUsername()));
        } catch (Exception e) {
            logger.info(String.format("Failed to save user %s", user.getUsername()));
            errorService.saveErrorWithNewTransaction(e);
        }
    }

    static class Increment {
        static int counter = 0;

        static int by1() {
            return counter++;
        }
    }
}
