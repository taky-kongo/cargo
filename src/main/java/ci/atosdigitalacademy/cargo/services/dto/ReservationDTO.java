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
    private String slug;
    private UserDTO user;
    private VoyageDTO voyage;
    private PaymentDTO payment;
    private SeatDTO seat;
}
