package ci.atosdigitalacademy.cargo.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRegisterCompanyDTO {

    @JsonIgnoreProperties({"voyages", "user"})
    private CompanyDTO company;

    @JsonIgnoreProperties({"id"})
    private UserDTO user;
}
