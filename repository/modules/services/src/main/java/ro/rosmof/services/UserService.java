package ro.rosmof.services;

import ro.rosmof.model.entities.User;

import java.util.List;

public interface UserService {

    void saveUserLists(List<User> list);

    void saveUserWithException(User user);

    void saveUser(User user);
}
