package ci.atosdigitalacademy.cargo.repositories;

import ci.atosdigitalacademy.cargo.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findBySlug(String slug);
}
