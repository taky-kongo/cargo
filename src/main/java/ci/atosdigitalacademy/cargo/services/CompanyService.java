package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.CompanyDTO;

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

    void deleteCompany(Long id);

    CompanyDTO partialUpdate(CompanyDTO company, Long id);
}