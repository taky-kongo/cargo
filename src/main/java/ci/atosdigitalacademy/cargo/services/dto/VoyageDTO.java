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
public class VoyageDTO {

    private Long id;
    private String start;
    private String destination;
    private LocalDate dateVoyage;
    private String time;
    private String slug;
    private CompanyDTO company;
}
