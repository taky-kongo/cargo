package ci.atosdigitalacademy.cargo.repositories;

import ci.atosdigitalacademy.cargo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);
    Optional<Role> findBySlug(String slug);
}
