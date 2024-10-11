package ci.atosdigitalacademy.cargo.services.Impl;

import ci.atosdigitalacademy.cargo.models.Reservation;
import ci.atosdigitalacademy.cargo.models.Role;
import ci.atosdigitalacademy.cargo.repository.ReservationRepository;
import ci.atosdigitalacademy.cargo.services.ReservationService;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import ci.atosdigitalacademy.cargo.services.mapper.ReservationMapper;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private  final ReservationMapper reservationMapper;
    private final ReservationService reservationService;

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        log.debug("Request to update Reservation : {}",reservationDTO);
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservation);
    }

    @Override
    public ReservationDTO update(ReservationDTO reservationDTO) {
        log.debug("Request to update role user: {}", reservationDTO);
        return findOne(reservationDTO.getId()).map(existingReservation->{
            existingReservation.setDateReservation(reservationDTO.getDateReservation());
            existingReservation.setDepart(reservationDTO.getDepart());
            existingReservation.setNmbrTicket(reservationDTO.getNmbrTicket());
            existingReservation.setVoyage(reservationDTO.getVoyage());
            return save(existingReservation);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<ReservationDTO> findOne(Long id) {
        log.debug("Request to get role user: {}", id);
        return reservationRepository.findById(id).map(reservation -> {
            return reservationMapper.toDto(reservation);
        });

    }

    @Override
    public List<ReservationDTO> findAll() {
        log.debug("Request to find");
        return reservationRepository.findAll().stream().map(reservation -> {
            return reservationMapper.toDto(reservation);
        }).toList();

    }

    @Override
    public ReservationDTO saveReservation(ReservationDTO reservationDTO) {
        final String slug = SlugifyUtils.generate(reservationDTO.getDateReservation().toString());
        reservationDTO.setSlug(slug);
        return save(reservationDTO);
    }

    @Override
    public void delete(Long id) { log.debug("Request to delete role user:{}",id); reservationRepository.deleteById(id);}

    @Override
    public ReservationDTO updatetotal(ReservationDTO reservationDTO, Long id) {
        reservationDTO.setId(id);
        return update(reservationDTO);
    }

    @GetMapping
    public List<ReservationDTO> getAll(){
        log.debug("REST request to get All");
        return reservationService.findAll();
    }

}
