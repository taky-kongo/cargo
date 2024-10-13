package ci.atosdigitalacademy.cargo.repository;

import ci.atosdigitalacademy.cargo.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findBySlug(String slug);
}
