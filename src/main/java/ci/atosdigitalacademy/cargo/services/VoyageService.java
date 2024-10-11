package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import ci.atosdigitalacademy.cargo.services.dto.VoyageDTO;

import java.util.List;
import java.util.Optional;

public interface VoyageService {

    VoyageDTO save(VoyageDTO voyageDTO);

    VoyageDTO update(VoyageDTO voyageDTO);

    Optional<VoyageDTO> findOne(Long id);

    List<VoyageDTO> findAll();

    VoyageDTO saveVoyage(VoyageDTO voyageDTO);

    void delete(Long id);

    VoyageDTO updatetotal(VoyageDTO voyageDTO, Long id);

}
