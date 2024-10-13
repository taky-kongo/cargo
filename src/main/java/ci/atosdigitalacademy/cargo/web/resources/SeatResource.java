package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.services.SeatService;
import ci.atosdigitalacademy.cargo.services.dto.SeatDTO;
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
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/seats")
public class SeatResource {
    
    private final SeatService seatService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Request to save seat")
    @Operation(summary = "Save a new seat", description = "This endpoint allow to save seat")
    public SeatDTO save(@RequestBody SeatDTO seat) {
        log.debug("Rest request to save seat : {}", seat);
        return seatService.saveSeat(seat);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get seat by id"),
            @ApiResponse(responseCode = "404", description = "seat not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getSeatById(
            @Parameter(required = true, description = "ID of seat to be retrieved")
            @PathVariable Long id) {
        log.debug("Rest request to get seat by id : {}", id);
        Optional<SeatDTO> seat = seatService.findOne(id);
        if (seat.isPresent()) {
            return new ResponseEntity<>(seat.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Id of seat not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get seat by slug"),
            @ApiResponse(responseCode = "404", description = "seat not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getSeatBySlug(
            @Parameter(required = true, description = "Slug of seat to be retrieved")
            @PathVariable String slug) {
        log.debug("Rest request to get seat by slug : {}", slug);
        Optional<SeatDTO> seat = seatService.findBySlug(slug);
        if (seat.isPresent()) {
            return new ResponseEntity<>(seat.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Slug of seat not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all seats")
    @Operation(summary = "Get all seats", description = "This endpoint allow to get all seats")
    public List<SeatDTO> getAllSeats() {
        log.debug("Rest request to get all seats");
        return seatService.findAll();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update seat")
    @Operation(summary = "Update seat", description = "This endpoint allow to update seat")
    @ResponseStatus(HttpStatus.OK)
    public SeatDTO update(@RequestBody SeatDTO seat, @PathVariable Long id) {
        log.debug("Rest request to update seat : {}", seat);
        return seatService.update(seat, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to delete seat")
    @Operation(summary = "Delete seat", description = "This endpoint allow delete seat")
    public void delete(@PathVariable Long id) {
        log.debug("Rest request to delete seat : {}", id);
        seatService.delete(id);
    }
}
