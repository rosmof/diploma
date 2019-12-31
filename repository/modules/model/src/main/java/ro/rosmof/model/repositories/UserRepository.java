package ro.rosmof.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rosmof.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
