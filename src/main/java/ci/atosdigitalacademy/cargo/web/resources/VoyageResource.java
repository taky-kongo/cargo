package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.models.Voyage;
import ci.atosdigitalacademy.cargo.services.VoyageService;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import ci.atosdigitalacademy.cargo.services.dto.VoyageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voyage")
@Slf4j
@RequiredArgsConstructor
public class VoyageResource {

    private final VoyageService voyageService;

    @PostMapping
    public ResponseEntity<VoyageDTO> save(@RequestBody VoyageDTO voyageDTO){
        log.debug("REST request to save voyage {}", voyageDTO);
        VoyageDTO voyage = voyageService.saveVoyage(voyageDTO);
        return new ResponseEntity<>(voyage, HttpStatus.CREATED);
    }

    public void deleteVoyage(@PathVariable Long id) {
        log.info("REST Request to delete Voyage : {}", id);
        voyageService.delete(id);
    }
    @PutMapping("/{id}")
    public VoyageDTO update(@RequestBody VoyageDTO voyageDTO, @PathVariable Long id){
        log.debug("REST request to update voyage {}", voyageDTO);
        return voyageService.updatetotal(voyageDTO, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        log.debug("REST request to get one voyage {}", id);
        Optional<VoyageDTO> voyageDTO = voyageService.findOne(id);
        if(voyageDTO.isPresent()){
            return new ResponseEntity<>(voyageDTO.get(), HttpStatus.OK);
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
    public List<VoyageDTO> getAll(){
        log.debug("REST request to get all");
        return voyageService.findAll();
    }



}
