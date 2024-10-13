package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.services.ReservationService;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@Slf4j
@RequiredArgsConstructor
public class ReservationResource {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Request to save reservation")
    @Operation(summary = "Save a new reservation", description = "This endpoint allow to save reservation")
    public ReservationDTO save(@RequestBody ReservationDTO reservationDTO){
        log.debug("REST request to save reservation {}", reservationDTO);
        return reservationService.saveReservation(reservationDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to delete reservation")
    @Operation(summary = "Delete client", description = "This endpoint allow delete reservation")
    public void delete(@PathVariable Long id) {
        log.info("REST Request to delete Reservation : {}", id);
        reservationService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update reservation")
    @Operation(summary = "Update reservation", description = "This endpoint allow to update reservation")
    @ResponseStatus(HttpStatus.OK)
    public ReservationDTO update(@RequestBody ReservationDTO reservationDTO, @PathVariable Long id){
        log.debug("REST request to update reservation {}", reservationDTO);
        return reservationService.updatetotal(reservationDTO, id);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get reservation by id"),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getReservationById(
            @Parameter(required = true, description = "ID of reservation to be retrieved")
            @PathVariable Long id) {
        log.debug("REST request to get one reservation {}", id);
        Optional<ReservationDTO> reservationDTO = reservationService.findOne(id);
        if(reservationDTO.isPresent()){
            return new ResponseEntity<>(reservationDTO.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get reservation by slug"),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getReservationBySlug(
            @Parameter(required = true, description = "Slug of reservation to be retrieved")
            @PathVariable String slug) {
        log.debug("REST request to get one by slug {}", slug);
        Optional<ReservationDTO> reservationDTO = reservationService.findOneBySlug(slug);
        if(reservationDTO.isPresent()){
            return new ResponseEntity<>(reservationDTO.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all reservations")
    @Operation(summary = "Get all reservations", description = "This endpoint allow to get all reservations")
    public List<ReservationDTO> getAll(){
        log.debug("REST request to get all");
        return reservationService.findAll();
    }
}



