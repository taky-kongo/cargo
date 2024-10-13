package ci.atosdigitalacademy.cargo.repository;

import ci.atosdigitalacademy.cargo.models.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {

    Optional<Voyage> findBySlug(String slug);
}
