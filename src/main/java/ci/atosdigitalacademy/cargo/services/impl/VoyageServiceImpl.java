package ci.atosdigitalacademy.cargo.services.impl;

import ci.atosdigitalacademy.cargo.models.Voyage;
import ci.atosdigitalacademy.cargo.repositories.VoyageRepository;
import ci.atosdigitalacademy.cargo.services.VoyageService;
import ci.atosdigitalacademy.cargo.services.dto.VoyageDTO;
import ci.atosdigitalacademy.cargo.services.mapper.VoyageMapper;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoyageServiceImpl implements VoyageService {

    private  final VoyageRepository voyageRepository;
    private  final VoyageMapper voyageMapper;

    @Override
    public VoyageDTO save(VoyageDTO voyageDTO) {
        log.debug("Request to save Voyage : {}",voyageDTO);
        Voyage voyage = voyageMapper.toEntity(voyageDTO);
        voyage = voyageRepository.save(voyage);
        return voyageMapper.toDto(voyage);
    }

    @Override
    public VoyageDTO update(VoyageDTO voyageDTO) {
        log.debug("Request to update voyage user: {}", voyageDTO);
        return findOne(voyageDTO.getId()).map(existingVoyage->{
            existingVoyage.setDateVoyage(voyageDTO.getDateVoyage());
            existingVoyage.setTime(voyageDTO.getTime());
            existingVoyage.setStart(voyageDTO.getStart());
            existingVoyage.setDestination(voyageDTO.getDestination());
            return save(existingVoyage);
        }).orElseThrow(() -> new IllegalArgumentException("Voyage not found"));
    }

    @Override
    public Optional<VoyageDTO> findOne(Long id) {
        log.debug("Request to get voyage by id {}",  id);
        return voyageRepository.findById(id).map(voyage -> {
            return voyageMapper.toDto(voyage);
        });
    }

    @Override
    public Optional<VoyageDTO> findOneBySlug(String slug) {
        log.debug("Request to get voyage by slug {}", slug);
        return voyageRepository.findBySlug(slug).map(voyage -> {
            return voyageMapper.toDto(voyage);
        });
    }

    @Override
    public List<VoyageDTO> findAll() {
        log.debug("Request to find all voyages");
        return voyageRepository.findAll().stream().map(voyage-> {
            return voyageMapper.toDto(voyage);
        }).toList();
    }

    @Override
    public VoyageDTO saveVoyage(VoyageDTO voyageDTO) {
        log.debug("Request to save Voyage : {} with slug", voyageDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(voyageDTO.getStart()));
        voyageDTO.setSlug(slug);
        return save(voyageDTO);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete voyage user: {}",id);
        voyageRepository.deleteById(id);
    }

    @Override
    public VoyageDTO updateTotal(VoyageDTO voyageDTO, Long id) {
        log.debug("Request to update total voyage: {} with id {}",voyageDTO,id);
        voyageDTO.setId(id);
        return update(voyageDTO);
    }

    @Override
    public List<VoyageDTO> findByStartAndDestinationOrDateVoyage(String start, String destination, LocalDate date) {
        log.debug("Request to get voyage by start and destination");
        return voyageRepository.findByStartIgnoreCaseAndDestinationIgnoreCaseAndDateVoyageGreaterThanEqual(start, destination, date).stream().map(voyage -> {
            return voyageMapper.toDto(voyage);
        }).toList();
    }

    @Override
    public List<VoyageDTO> findVoyageByCompanyId(Long companyId) {
        log.debug("Request to get all voyages by company {}: ", companyId);
        return voyageRepository.findVoyageByCompanyId(companyId).stream().map(voyage -> {
            return voyageMapper.toDto(voyage);
        }).toList();
    }

    @Override
    public List<VoyageDTO> findVoyageByCompanyNameIgnoreCase(String companyName) {
        log.debug("Request to get all voyages by company name {}: ", companyName);
        return voyageRepository.findVoyageByCompanyNameIgnoreCase(companyName).stream().map(company -> {
            return voyageMapper.toDto(company);
        }).toList();
    }

    @GetMapping
    public List<VoyageDTO> getAll(){
        log.debug("Request to get All voyages");
        return voyageRepository.findAll().stream().map(voyage -> {
            return voyageMapper.toDto(voyage);
        }).toList();
    }
}
