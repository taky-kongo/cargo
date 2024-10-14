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
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private LocalDate dateCreation;

    private String slug;

    @JsonIgnoreProperties({"id"})
    private RoleDTO role;
}
