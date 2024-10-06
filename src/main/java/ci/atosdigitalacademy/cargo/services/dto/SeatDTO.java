package ci.atosdigitalacademy.cargo.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {

    private Long id;
    private String seatNumber;
    private String classe;
    private String status;
    private String slug;
    private ReservationDTO reservation;
}
