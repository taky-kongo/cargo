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

    private int seatNumber;

    private String classe;

    private boolean available;

    private String slug;

    private VoyageDTO voyage;
}
