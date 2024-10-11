package ci.atosdigitalacademy.cargo.repository;

import ci.atosdigitalacademy.cargo.models.Voyage;
import ci.atosdigitalacademy.cargo.services.dto.VoyageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {
}
