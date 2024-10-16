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
public class ReservationDTO {

    private Long id;

    private LocalDate dateReservation;

    private String status;

    private int seatNumber;

    private String slug;

    private ClientDTO client;

    private VoyageDTO voyage;

    private PaymentDTO payment;
}
