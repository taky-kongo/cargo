package ci.atosdigitalacademy.cargo.repository;

import ci.atosdigitalacademy.cargo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRole(String role);
}
