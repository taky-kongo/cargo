package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.Payment;
import ci.atosdigitalacademy.cargo.services.dto.PaymentDTO;
import ci.atosdigitalacademy.cargo.services.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentMapperImpl implements PaymentMapper {

    private final ModelMapper modelMapper;

    @Override
    public Payment toEntity(PaymentDTO dto) {
        return modelMapper.map(dto, Payment.class);
    }

    @Override
    public PaymentDTO toDto(Payment entity) {
        return modelMapper.map(entity, PaymentDTO.class);
    }
}
