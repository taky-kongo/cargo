package ci.atosdigitalacademy.cargo.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationClientDTO {

    private String name;

    private String email;

    private String phoneNumber;

    private LocalDate dateCreation;

    private String username;

    private String password;
}
