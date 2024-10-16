package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.CompanyDTO;
import ci.atosdigitalacademy.cargo.services.dto.RegistrationPersonDTO;
import ci.atosdigitalacademy.cargo.services.dto.ResponseRegisterCompanyDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    CompanyDTO save(CompanyDTO companyDTO);

    CompanyDTO saveCompany(CompanyDTO companyDTO);

    CompanyDTO updateCompany(CompanyDTO companyDTO);

    CompanyDTO updateCompany(CompanyDTO companyDTO,Long id);

    List<CompanyDTO> findAllCompany();

    Optional<CompanyDTO> findOne(Long id);

    Optional<CompanyDTO> findBySlug(String slug);

    Optional<CompanyDTO> findCompaniesByName(String name);

    void deleteCompany(Long id);

    CompanyDTO partialUpdate(CompanyDTO company, Long id);

    ResponseRegisterCompanyDTO registerCompany(RegistrationPersonDTO registrationPersonDTO);
}