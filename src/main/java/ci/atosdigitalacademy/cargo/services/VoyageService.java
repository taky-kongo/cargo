package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.VoyageDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoyageService {

    VoyageDTO save(VoyageDTO voyageDTO);

    VoyageDTO update(VoyageDTO voyageDTO);

    Optional<VoyageDTO> findOne(Long id);

    Optional<VoyageDTO> findOneBySlug(String slug);

    List<VoyageDTO> findAll();

    VoyageDTO saveVoyage(VoyageDTO voyageDTO);

    void delete(Long id);

    VoyageDTO updateTotal(VoyageDTO voyageDTO, Long id);

    List<VoyageDTO> findByStartAndDestinationOrDateVoyage(String start, String destination, LocalDate date);

    List<VoyageDTO> findVoyageByCompanyId(Long companyId);

    List<VoyageDTO> findVoyageByCompanyNameIgnoreCase(String companyName);

    List<VoyageDTO> findVoyageByAmount(Double amount);
}
