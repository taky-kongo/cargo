package ci.atosdigitalacademy.cargo.services.Impl;

import ci.atosdigitalacademy.cargo.services.CompanyService;
import ci.atosdigitalacademy.cargo.models.Company;
import ci.atosdigitalacademy.cargo.repositories.CompanyRepository;
import ci.atosdigitalacademy.cargo.services.dto.CompanyDTO;
import ci.atosdigitalacademy.cargo.services.mapper.CompanyMapper;
import ci.atosdigitalacademy.cargo.services.mapping.CompanyMapping;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;
    @Override
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        log.debug("Request to sva Company: {}", companyDTO);
        companyDTO.setSlug(SlugifyUtils.generate("qezsesz"));
        Company company= companyMapper.toEntity(companyDTO);
        company=companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        return findOne(companyDTO.getId()).map(company -> {
            company.setName(companyDTO.getName());
            company.setEmail(companyDTO.getEmail());
            company.setDateCreation(companyDTO.getDateCreation());
            company.setPassword(companyDTO.getPassword());
            company.setRole(companyDTO.getRole());
            return saveCompany(company);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO, Long id) {
        log.debug("Request to update :{} ",id);
        companyDTO.setId(id);
        return updateCompany(companyDTO);
    }

    @Override
    public List<CompanyDTO> findAllCompany() {
        log.debug("Request to get all company");
        return companyRepository.findAll().stream().map(company -> {
            return companyMapper.toDto(company);
        }).toList();
    }

    @Override
    public Optional<CompanyDTO> findOne(Long id) {
        log.debug("Request to get Company : {}",id);
        return companyRepository.findById(id).map(company -> {
            return companyMapper.toDto(company);
        });
    }

    @Override
    public Optional<CompanyDTO> findBySlug(String slug) {
      log.debug("Request to get Company by slug ");
      return companyRepository.findCompanyBySlug(slug).map(company -> {
          return companyMapper.toDto(company);
      });
    }

    @Override
    public void deleteCompany(Long id) {
        log.debug("Request to delete company :{}", id);
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyDTO partialUpdate(CompanyDTO companyDTO, Long id) {
        log.debug("Request to partial update Company by id : {}", companyDTO);
        return companyRepository.findById(id).map(company -> {
            CompanyMapping.partialUpdate(company, companyDTO);
            return company;
        }).map(companyRepository::save).map(companyMapper::toDto).orElse(null);
    }
}
