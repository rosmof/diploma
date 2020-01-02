package ro.rosmof.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rosmof.model.entities.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
