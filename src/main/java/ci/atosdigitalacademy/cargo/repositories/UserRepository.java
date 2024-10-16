package ci.atosdigitalacademy.cargo.repositories;

import ci.atosdigitalacademy.cargo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String pseudo);
}
