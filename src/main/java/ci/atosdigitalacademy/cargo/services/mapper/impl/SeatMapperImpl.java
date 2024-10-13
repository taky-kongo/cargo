package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.Seat;
import ci.atosdigitalacademy.cargo.services.dto.SeatDTO;
import ci.atosdigitalacademy.cargo.services.mapper.SeatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SeatMapperImpl implements SeatMapper {

    private final ModelMapper modelMapper;

    @Override
    public Seat toEntity(SeatDTO dto) {
        return modelMapper.map(dto, Seat.class);
    }

    @Override
    public SeatDTO toDto(Seat entity) {
        return modelMapper.map(entity, SeatDTO.class);
    }
}
