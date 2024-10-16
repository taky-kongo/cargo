package ci.atosdigitalacademy.cargo.repositories;

import ci.atosdigitalacademy.cargo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findBySlug(String slug);
    Optional<Client> findClientByUserId(Long userId);
}
