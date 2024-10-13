package ci.atosdigitalacademy.cargo.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoyageDTO {

    private Long id;

    private String start;

    private String destination;

    private LocalDate dateVoyage;

    private String time;

    private String slug;

    private List<ReservationDTO> reservations;

    private CompanyDTO company;

    private List<SeatDTO> seats;
}
