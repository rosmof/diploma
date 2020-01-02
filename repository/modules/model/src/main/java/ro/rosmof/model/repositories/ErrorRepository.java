package ro.rosmof.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rosmof.model.entities.Error;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {
}
