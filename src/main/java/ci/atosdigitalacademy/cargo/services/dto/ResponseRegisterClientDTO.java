package ci.atosdigitalacademy.cargo.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRegisterClientDTO {

    @JsonIgnoreProperties({"reservations", "user"})
    private ClientDTO client;

    @JsonIgnoreProperties({"id"})
    private UserDTO user;
}
