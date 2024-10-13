package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.TypePayment;
import ci.atosdigitalacademy.cargo.services.dto.TypePaymentDTO;
import ci.atosdigitalacademy.cargo.services.mapper.TypePaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TypePaymentMapperImpl implements TypePaymentMapper {

    private final ModelMapper modelMapper;

    @Override
    public TypePayment toEntity(TypePaymentDTO dto) {
        return modelMapper.map(dto, TypePayment.class);
    }

    @Override
    public TypePaymentDTO toDto(TypePayment entity) {
        return modelMapper.map(entity, TypePaymentDTO.class);
    }
}
