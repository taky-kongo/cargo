package ci.atosdigitalacademy.cargo.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private Double amount;
    private LocalDate datePayment;
    private String slug;
    private TypePaymentDTO typePayment;
    private ReservationDTO reservation;
}
