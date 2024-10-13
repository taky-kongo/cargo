package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.services.TypePaymentService;
import ci.atosdigitalacademy.cargo.services.dto.TypePaymentDTO;
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
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/typePayments")
public class TypePaymentResource {

    private final TypePaymentService typePaymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Request to save type payment")
    @Operation(summary = "Save a new type payment", description = "This endpoint allow to save type payment")
    public TypePaymentDTO saveTypePayment(@RequestBody TypePaymentDTO typePayment) {
        log.debug("Rest request to save TypePayment : {}", typePayment);
        return typePaymentService.saveTypePayment(typePayment);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get type payment by id"),
            @ApiResponse(responseCode = "404", description = "type payment not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getTypePaymentById(
            @Parameter(required = true, description = "ID of typePayment to be retrieved")
            @PathVariable Long id) {
        log.debug("Rest request to get type payment by id : {}", id);
        Optional<TypePaymentDTO> typePayment = typePaymentService.findOne(id);
        if (typePayment.isPresent()) {
            return new ResponseEntity<>(typePayment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Id of typePayment not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get typePayment by slug"),
            @ApiResponse(responseCode = "404", description = "TypePayment not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getTypePaymentBySlug(
            @Parameter(required = true, description = "Slug of to be retrieved")
            @PathVariable String slug) {
        log.debug("Rest request to get type payment by slug : {}", slug);
        Optional<TypePaymentDTO> typePayment = typePaymentService.findBySlug(slug);
        if (typePayment.isPresent()) {
            return new ResponseEntity<>(typePayment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Slug of typePayment not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all typePayment")
    @Operation(summary = "Get all typePayment", description = "This endpoint allow to get all type payment")
    public List<TypePaymentDTO> getAllTypePayment() {
        log.debug("Rest request to get all typePayment");
        return typePaymentService.findAllTypePayment();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update typePayment")
    @Operation(summary = "Update typePayment", description = "This endpoint allow to update typePayment")
    @ResponseStatus(HttpStatus.OK)
    public TypePaymentDTO updateTypePayment(@RequestBody TypePaymentDTO typePayment, @PathVariable Long id) {
        log.debug("Rest request to update TypePayment : {}", typePayment);
        return typePaymentService.updateTypePayment( typePayment, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to delete typePayment")
    @Operation(summary = "Delete typePayment", description = "This endpoint allow delete typePayment")
    public void deleteTypePayment(@PathVariable Long id) {
        log.debug("Rest request to delete typePayment : {}", id);
        typePaymentService.deleteTypePayment(id);
    }
}
