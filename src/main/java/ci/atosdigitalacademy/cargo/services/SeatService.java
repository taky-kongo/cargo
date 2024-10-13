package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.SeatDTO;

import java.util.List;
import java.util.Optional;

public interface SeatService {

    SeatDTO save(SeatDTO seatDTO);

    SeatDTO saveSeat(SeatDTO seatDTO);

    SeatDTO update(SeatDTO seatDTO);

    SeatDTO update(SeatDTO seatDTO, Long id);

    Optional<SeatDTO> findOne(Long id);

    Optional<SeatDTO> findBySlug(String slug);

    List<SeatDTO> findAll();

    void delete(Long id);
}
