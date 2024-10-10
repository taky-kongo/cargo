package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.Voyage;
import ci.atosdigitalacademy.cargo.services.dto.VoyageDTO;
import ci.atosdigitalacademy.cargo.services.mapper.VoyageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class VoyageMapperImpl implements VoyageMapper {

    private final ModelMapper modelMapper;

    @Override
    public Voyage toEntity(VoyageDTO dto) {
        modelMapper.map(dto, Voyage.class);
    }

    @Override
    public VoyageDTO toDto(Voyage entity) {
        return modelMapper.map(entity, VoyageDTO.class);
    }
}
