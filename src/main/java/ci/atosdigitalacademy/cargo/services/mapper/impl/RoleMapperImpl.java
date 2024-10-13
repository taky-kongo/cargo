package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.Role;
import ci.atosdigitalacademy.cargo.services.dto.RoleDTO;
import ci.atosdigitalacademy.cargo.services.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RoleMapperImpl implements RoleMapper {

    private final ModelMapper modelMapper;

    @Override
    public Role toEntity(RoleDTO dto) {
        return modelMapper.map(dto, Role.class);
    }

    @Override
    public RoleDTO toDto(Role entity) {
        return modelMapper.map(entity, RoleDTO.class);
    }
}
