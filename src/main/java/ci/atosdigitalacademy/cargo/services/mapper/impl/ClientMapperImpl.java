package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.Client;
import ci.atosdigitalacademy.cargo.services.dto.ClientDTO;
import ci.atosdigitalacademy.cargo.services.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientMapperImpl implements ClientMapper {

    private final ModelMapper modelMapper;

    @Override
    public Client toEntity(ClientDTO dto) {
        return modelMapper.map(dto, Client.class);
    }

    @Override
    public ClientDTO toDto(Client entity) {
        return modelMapper.map(entity, ClientDTO.class);
    }
}
