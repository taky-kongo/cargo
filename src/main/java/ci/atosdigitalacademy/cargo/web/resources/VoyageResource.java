package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.services.VoyageService;
import ci.atosdigitalacademy.cargo.services.dto.VoyageDTO;
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
@RequestMapping("/api/voyages")
@Slf4j
@RequiredArgsConstructor
public class VoyageResource {

    private final VoyageService voyageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Request to save voyage")
    @Operation(summary = "Save a new voyage", description = "This endpoint allow to save voyage")
    public VoyageDTO save(@RequestBody VoyageDTO voyageDTO){
        log.debug("REST request to save voyage {}", voyageDTO);
        return voyageService.saveVoyage(voyageDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to delete voyage")
    @Operation(summary = "Delete voyage", description = "This endpoint allow delete voyage")
    public void delete(@PathVariable Long id) {
        log.info("REST Request to delete Voyage : {}", id);
        voyageService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update voyage")
    @Operation(summary = "Update voyage", description = "This endpoint allow to update voyage")
    @ResponseStatus(HttpStatus.OK)
    public VoyageDTO update(@RequestBody VoyageDTO voyageDTO, @PathVariable Long id){
        log.debug("REST request to update voyage {}", voyageDTO);
        return voyageService.updateTotal(voyageDTO, id);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get voyage by id"),
            @ApiResponse(responseCode = "404", description = "voyage not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getVoyageById(
            @Parameter(required = true, description = "ID of voyage to be retrieved")
            @PathVariable Long id) {
        log.debug("REST request to get one voyage {}", id);
        Optional<VoyageDTO> voyageDTO = voyageService.findOne(id);
        if(voyageDTO.isPresent()){
            return new ResponseEntity<>(voyageDTO.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Voyage not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get voyage by slug"),
            @ApiResponse(responseCode = "404", description = "voyage not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getVoyageBySlug(
            @Parameter(required = true, description = "Slug of voyage to be retrieved")
            @PathVariable String slug) {
        log.debug("REST request to get one by slug {}", slug);
        Optional<VoyageDTO> voyageDTO = voyageService.findOneBySlug(slug);
        if(voyageDTO.isPresent()){
            return new ResponseEntity<>(voyageDTO.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("voyage not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all voyages")
    @Operation(summary = "Get all voyages", description = "This endpoint allow to get all voyages")
    public List<VoyageDTO> getAllVoyages(){
        log.debug("REST request to get all voyages");
        return voyageService.findAll();
    }
}
