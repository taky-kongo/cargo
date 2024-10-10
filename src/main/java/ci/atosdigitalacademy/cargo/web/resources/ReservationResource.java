package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.services.ReservationService;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
@Slf4j
@RequiredArgsConstructor
public class ReservationResource {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservationDTO){
        log.debug("REST request to save reservation {}", reservationDTO);
        ReservationDTO reservation = reservationService.saveReservation(reservationDTO);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
}
