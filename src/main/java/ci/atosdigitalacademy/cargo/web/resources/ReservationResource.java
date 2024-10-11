package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.services.ReservationService;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    public void deleteReservation(@PathVariable Long id) {
        log.info("REST Request to delete Reservation : {}", id);
        reservationService.delete(id);
}
    @PutMapping("/{id}")
    public ReservationDTO update(@RequestBody ReservationDTO reservationDTO, @PathVariable Long id){
        log.debug("REST request to update reservation {}", reservationDTO);
        return reservationService.updatetotal(reservationDTO, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        log.debug("REST request to get one reservetion {}", id);
        Optional<ReservationDTO> reservationDTO = reservationService.findOne(id);
        if(reservationDTO.isPresent()){
            return new ResponseEntity<>(reservationDTO.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        log.debug("REST request to delete {}", id);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getOneBySlug(@PathVariable String slug){
        log.debug("REST request to get one by slug {}", slug);
        return null;
    }


    @GetMapping
    public List<ReservationDTO> getAll(){
        log.debug("REST request to get all");
        return reservationService.findAll();
    }

}



