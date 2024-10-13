package ci.atosdigitalacademy.cargo.services.Impl;

import ci.atosdigitalacademy.cargo.models.Reservation;
import ci.atosdigitalacademy.cargo.repositories.ReservationRepository;
import ci.atosdigitalacademy.cargo.services.ReservationService;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import ci.atosdigitalacademy.cargo.services.mapper.ReservationMapper;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private  final ReservationMapper reservationMapper;

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        log.debug("Request to save Reservation : {}",reservationDTO);
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservation);
    }

    @Override
    public ReservationDTO update(ReservationDTO reservationDTO) {
        log.debug("Request to update reservation: {}", reservationDTO);
        return findOne(reservationDTO.getId()).map(existingReservation->{
            existingReservation.setDateReservation(reservationDTO.getDateReservation());
            existingReservation.setVoyage(reservationDTO.getVoyage());
            return save(existingReservation);
        }).orElseThrow(() -> new IllegalArgumentException("Reservation id not found"));
    }

    @Override
    public Optional<ReservationDTO> findOne(Long id) {
        log.debug("Request to get reservation : {}", id);
        return reservationRepository.findById(id).map(reservation -> {
            return reservationMapper.toDto(reservation);
        });

    }

    @Override
    public Optional<ReservationDTO> findOneBySlug(String slug) {
        log.debug("Request to get reservation by slug: {}", slug);
        return reservationRepository.findBySlug(slug).map(reservation -> {
            return reservationMapper.toDto(reservation);
        });
    }

    @Override
    public List<ReservationDTO> findAll() {
        log.debug("Request to find all reservations");
        return reservationRepository.findAll().stream().map(reservation -> {
            return reservationMapper.toDto(reservation);
        }).toList();

    }

    @Override
    public ReservationDTO saveReservation(ReservationDTO reservationDTO) {
        log.debug("Request to save Reservation : {} with slug", reservationDTO);
        final String slug = SlugifyUtils.generate(reservationDTO.getDateReservation().toString());
        reservationDTO.setSlug(slug);
        return save(reservationDTO);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete reservation: {}",id);
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationDTO updateTotal(ReservationDTO reservationDTO, Long id) {
        log.debug("Request to update reservation {} by id", reservationDTO);
        reservationDTO.setId(id);
        return update(reservationDTO);
    }

}
