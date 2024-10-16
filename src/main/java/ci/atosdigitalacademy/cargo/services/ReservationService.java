package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    ReservationDTO save(ReservationDTO reservationDTO);

    ReservationDTO update(ReservationDTO reservationDTO);

    Optional<ReservationDTO> findOne(Long id);

    Optional<ReservationDTO> findOneBySlug(String slug);

    List<ReservationDTO> findAll();

    ReservationDTO saveReservation(ReservationDTO reservationDTO);

    void delete(Long id);

    ReservationDTO updateTotal(ReservationDTO reservationDTO, Long id);

    void cancelledReservation(Long reservationID);

}
