package ci.atosdigitalacademy.cargo.services.mapping;

import ci.atosdigitalacademy.cargo.models.Company;
import ci.atosdigitalacademy.cargo.services.dto.CompanyDTO;

public final class CompanyMapping {

    private CompanyMapping() {}

    public static void partialUpdate(Company company, CompanyDTO companyDTO) {
        if (companyDTO.getName() != null) {
            company.setName(companyDTO.getName());
        }
        if (companyDTO.getPhoneNumber() != null) {
            company.setPhoneNumber(companyDTO.getPhoneNumber());
        }
        if (companyDTO.getEmail() != null) {
            company.setEmail(companyDTO.getEmail());
        }
        if (companyDTO.getEmail() != null) {
            company.setEmail(companyDTO.getEmail());
        }
        if (companyDTO.getDateCreation() != null) {
            company.setDateCreation(companyDTO.getDateCreation());
        }
    }
}
