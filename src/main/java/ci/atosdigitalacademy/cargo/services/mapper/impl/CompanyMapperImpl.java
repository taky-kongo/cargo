package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.Company;
import ci.atosdigitalacademy.cargo.services.dto.CompanyDTO;
import ci.atosdigitalacademy.cargo.services.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyMapperImpl implements CompanyMapper {

    private final ModelMapper modelMapper;

    @Override
    public Company toEntity(CompanyDTO dto) {
        return modelMapper.map(dto, Company.class);
    }

    @Override
    public CompanyDTO toDto(Company entity) {
        return modelMapper.map(entity, CompanyDTO.class);
    }
}
