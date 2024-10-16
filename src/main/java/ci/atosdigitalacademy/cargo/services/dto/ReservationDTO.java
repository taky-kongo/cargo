package ci.atosdigitalacademy.cargo.services.dto;

import ci.atosdigitalacademy.cargo.models.enumeration.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Long id;

    private LocalDate dateReservation;

    private ReservationType status;

    private String slug;

    private ClientDTO client;

    private VoyageDTO voyage;

    private PaymentDTO payment;

}
