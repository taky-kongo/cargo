package ci.atosdigitalacademy.cargo.repositories;

import ci.atosdigitalacademy.cargo.models.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {

    Optional<Voyage> findBySlug(String slug);
    List<Voyage> findByStartIgnoreCaseAndDestinationIgnoreCaseAndDateVoyageGreaterThanEqual(String start, String destination, LocalDate date);
    List<Voyage> findVoyageByCompanyId(Long companyId);
    List<Voyage> findVoyageByCompanyNameIgnoreCase(String companyName);
    List<Voyage> findVoyageByAmountLessThanEqual(double amount);
}
