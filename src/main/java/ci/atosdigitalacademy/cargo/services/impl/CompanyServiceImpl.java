package ci.atosdigitalacademy.cargo.services.impl;

import ci.atosdigitalacademy.cargo.security.AuthorityConstants;
import ci.atosdigitalacademy.cargo.services.CompanyService;
import ci.atosdigitalacademy.cargo.models.Company;
import ci.atosdigitalacademy.cargo.repositories.CompanyRepository;
import ci.atosdigitalacademy.cargo.services.RoleService;
import ci.atosdigitalacademy.cargo.services.UserService;
import ci.atosdigitalacademy.cargo.services.dto.*;
import ci.atosdigitalacademy.cargo.services.mapper.CompanyMapper;
import ci.atosdigitalacademy.cargo.services.mapping.CompanyMapping;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        log.debug("Request to save Company : {}", companyDTO);
        Company company = companyMapper.toEntity(companyDTO);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Override
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        log.debug("Request to save Company: {} with slug", companyDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(companyDTO.getName()));
        companyDTO.setSlug(slug);
        return save(companyDTO);
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        log.debug("Request to update Company : {}", companyDTO);
        return findOne(companyDTO.getId()).map(company -> {
            company.setName(companyDTO.getName());
            company.setEmail(companyDTO.getEmail());
            company.setDateCreation(companyDTO.getDateCreation());
            company.setPhoneNumber(companyDTO.getPhoneNumber());
            return save(company);
        }).orElseThrow(() -> new IllegalArgumentException("Company not found"));
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO, Long id) {
        log.debug("Request to update company: {} by id {}", companyDTO, id);
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
        log.debug("Request to get Company: {}",id);
        return companyRepository.findById(id).map(company -> {
            return companyMapper.toDto(company);
        });
    }

    @Override
    public Optional<CompanyDTO> findBySlug(String slug) {
        log.debug("Request to get Company by slug: {}", slug);
            return companyRepository.findCompanyBySlug(slug).map(company -> {
                return companyMapper.toDto(company);
            });
    }

    @Override
    public Optional<CompanyDTO> findCompaniesByName(String name) {
        log.debug("Request to get Company by name: {}", name;
        return companyRepository.findCompaniesByName(name).map(company -> {
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
        log.debug("Request to partial update Company by id : {}", id);
        return companyRepository.findById(id).map(company -> {
            CompanyMapping.partialUpdate(company, companyDTO);
            return company;
        }).map(companyRepository::save).map(companyMapper::toDto).orElse(null);
    }

    @Override
    @Transactional
    public ResponseRegisterCompanyDTO registerCompany(RegistrationPersonDTO registrationPersonDTO) {
        log.debug("Request to register company {}", registrationPersonDTO);
        List<RoleDTO> roles = roleService.findByRole(AuthorityConstants.ROLE_COMPANY);
        UserDTO user = modelMapper.map(registrationPersonDTO, UserDTO.class);
        user.setPassword(bCryptPasswordEncoder.encode(registrationPersonDTO.getPassword()));
        user.setRoles(roles);
        user = userService.save(user);

        CompanyDTO company = modelMapper.map(registrationPersonDTO, CompanyDTO.class);
        company.setUser(user);
        company = save(company);

        ResponseRegisterCompanyDTO responseRegisterCompanyDTO = new ResponseRegisterCompanyDTO();
        responseRegisterCompanyDTO.setCompany(company);
        responseRegisterCompanyDTO.setUser(user);
        return responseRegisterCompanyDTO;
    }
}
