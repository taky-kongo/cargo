package ci.atosdigitalacademy.cargo.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String phoneNumber;

    private String slug;

    private LocalDate datePayment;

    @JsonIgnoreProperties({"payments"})
    private TypePaymentDTO typePayment;
}
