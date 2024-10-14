package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.services.PaymentService;
import ci.atosdigitalacademy.cargo.services.dto.CompanyDTO;
import ci.atosdigitalacademy.cargo.services.dto.PaymentDTO;
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
@RequestMapping("/api/payments")
public class PaymentResource {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Request to save type payment")
    @Operation(summary = "Save a new payment", description = "This endpoint allow to save payment")
    public PaymentDTO save(@RequestBody PaymentDTO payment) {
        log.debug("Rest request to save payment : {}", payment);
        return paymentService.savePayment(payment);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get payment by id"),
            @ApiResponse(responseCode = "404", description = "Payment not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getPaymentById(
            @Parameter(required = true, description = "ID of payment to be retrieved")
            @PathVariable Long id) {
        log.debug("Rest request to get payment by id : {}", id);
        Optional<PaymentDTO> payment = paymentService.findOne(id);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Id of payment not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get payment by slug"),
            @ApiResponse(responseCode = "404", description = "payment not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getPaymentBySlug(
            @Parameter(required = true, description = "Slug of to be retrieved")
            @PathVariable String slug) {
        log.debug("Rest request to get payment by slug : {}", slug);
        Optional<PaymentDTO> payment = paymentService.findBySlug(slug);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Slug of payment not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all payments")
    @Operation(summary = "Get all payments", description = "This endpoint allow to get all payments")
    public List<PaymentDTO> getAllPayment() {
        log.debug("Rest request to get all payments");
        return paymentService.findAll();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update payment")
    @Operation(summary = "Update payment", description = "This endpoint allow to update payment")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDTO update(@RequestBody PaymentDTO payment, @PathVariable Long id) {
        log.debug("Rest request to update payment : {}", payment);
        return paymentService.update( payment, id);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to partial update payment")
    @Operation(summary = "Update payment", description = "This endpoint allow to partial update payment")
    public PaymentDTO partialUpdate(@PathVariable Long id, @RequestBody PaymentDTO payment) {
        log.debug("Rest request to partial update payment : {}", payment);
        return paymentService.partialUpdate(payment, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to delete payment")
    @Operation(summary = "Delete payment", description = "This endpoint allow delete payment")
    public void delete(@PathVariable Long id) {
        log.debug("Rest request to delete payment : {}", id);
        paymentService.delete(id);
    }
}
