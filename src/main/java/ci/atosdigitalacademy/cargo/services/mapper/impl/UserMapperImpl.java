package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.User;
import ci.atosdigitalacademy.cargo.services.dto.UserDTO;
import ci.atosdigitalacademy.cargo.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public UserDTO toDto(User entity) {
        return modelMapper.map(entity, UserDTO.class);
    }
}
