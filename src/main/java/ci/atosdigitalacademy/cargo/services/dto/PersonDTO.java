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
public class PersonDTO {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private LocalDate dateCreation;

    private String slug;

    private UserDTO user;
}
