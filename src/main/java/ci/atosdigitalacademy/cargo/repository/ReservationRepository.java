package ci.atosdigitalacademy.cargo.repository;

import ci.atosdigitalacademy.cargo.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByDestinationIgnoreCaseOrDepartIgnoreCaseOrDateReservation(String destination, String depart, String dateReservation);
}
