package ci.atosdigitalacademy.cargo.services.mapper.impl;

import ci.atosdigitalacademy.cargo.models.Reservation;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import ci.atosdigitalacademy.cargo.services.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReservationMapperImpl implements ReservationMapper {

    private final ModelMapper modelMapper;

    @Override
    public Reservation toEntity(ReservationDTO dto) {
        return modelMapper.map(dto, Reservation.class);
    }

    @Override
    public ReservationDTO toDto(Reservation entity) {
        return modelMapper.map(entity, ReservationDTO.class);
    }
}
