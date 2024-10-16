package ci.atosdigitalacademy.cargo.services.impl;

import ci.atosdigitalacademy.cargo.models.Seat;
import ci.atosdigitalacademy.cargo.repositories.SeatRepository;
import ci.atosdigitalacademy.cargo.services.SeatService;
import ci.atosdigitalacademy.cargo.services.VoyageService;
import ci.atosdigitalacademy.cargo.services.dto.SeatDTO;
import ci.atosdigitalacademy.cargo.services.dto.VoyageDTO;
import ci.atosdigitalacademy.cargo.services.mapper.SeatMapper;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements SeatService {

    private final SeatMapper seatMapper;
    private final SeatRepository seatRepository;
    private final VoyageService voyageService;

    @Override
    public SeatDTO save(SeatDTO seatDTO) {
        log.debug("Request to save seat: {}", seatDTO);
        Optional<VoyageDTO> voyage = voyageService.findOne(seatDTO.getVoyage().getId());
        if (voyage.isPresent()) {
            seatDTO.setVoyage(voyage.get());
        } else {
            throw new IllegalArgumentException("Voyage not found");
        }
        Seat seat = seatMapper.toEntity(seatDTO);
        seat = seatRepository.save(seat);
        return seatMapper.toDto(seat);
    }

    @Override
    public SeatDTO saveSeat(SeatDTO seatDTO) {
        log.debug("Request to save seat {} with slug", seatDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(seatDTO.getSeatNumber()));
        seatDTO.setSlug(slug);
        return save(seatDTO);
    }

    @Override
    public SeatDTO update(SeatDTO seatDTO) {
        log.debug("Request to update client {}", seatDTO);
        return findOne(seatDTO.getId()).map(seat -> {
            seat.setSeatNumber(seat.getSeatNumber());
            seat.setClasse(seat.getClasse());
            seat.setAvailable(seat.isAvailable());
            return save(seat);
        }).orElseThrow(() -> new IllegalArgumentException("Seat not found"));
    }

    @Override
    public SeatDTO update(SeatDTO seatDTO, Long id) {
        log.debug("Request to update client {} with id {}", seatDTO, id);
        seatDTO.setId(id);
        return update(seatDTO);
    }

    @Override
    public Optional<SeatDTO> findOne(Long id) {
        log.debug("Request to get seat by id {}", id);
        return seatRepository.findById(id).map(seat -> {
            return seatMapper.toDto(seat);
        });
    }

    @Override
    public Optional<SeatDTO> findBySlug(String slug) {
        log.debug("Request to get seat by slug {}", slug);
        return seatRepository.findBySlug(slug).map(seat -> {
            return seatMapper.toDto(seat);
        });
    }

    @Override
    public List<SeatDTO> findAll() {
        log.debug("Request to get all seats");
        return seatRepository.findAll().stream().map(seat -> {
            return seatMapper.toDto(seat);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete seat {}", id);
        seatRepository.deleteById(id);
    }
}
