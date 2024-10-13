package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.Company;
import ci.atosdigitalacademy.cargo.services.dto.CompanyDTO;
import ci.atosdigitalacademy.cargo.services.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CompanyMapperImpl implements CompanyMapper {

    private final ModelMapper modelMapper;

    @Override
    public Company toEntity(CompanyDTO dto) {
        log.debug("Mapping CompanyDTO to Company ");
        return modelMapper.map(dto, Company.class);
    }

    @Override
    public CompanyDTO toDto(Company entity) {
        log.debug("Mapping Company to CompanyDTO");
        return modelMapper.map(entity, CompanyDTO.class);
    }
}
