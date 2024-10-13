package ci.atosdigitalacademy.cargo.services.Impl;

import ci.atosdigitalacademy.cargo.models.Voyage;
import ci.atosdigitalacademy.cargo.repository.VoyageRepository;
import ci.atosdigitalacademy.cargo.services.VoyageService;
import ci.atosdigitalacademy.cargo.services.dto.VoyageDTO;
import ci.atosdigitalacademy.cargo.services.mapper.VoyageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
        log.debug("Request to update Voyage : {}",voyageDTO);
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
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<VoyageDTO> findOne(Long id) {
        log.debug("Request to get voyage user: {}", id);
        return voyageRepository.findById(id).map(voyage -> {
            return voyageMapper.toDto(voyage);
        });
    }

    @Override
    public List<VoyageDTO> findAll() {
        log.debug("Request to find");
        return voyageRepository.findAll().stream().map(voyage-> {
            return voyageMapper.toDto(voyage);
        }).toList();
    }

    @Override
    public VoyageDTO saveVoyage(VoyageDTO voyageDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {log.debug("Request to delete voyage user:{}",id); voyageRepository.deleteById(id);

    }

    @Override
    public VoyageDTO updatetotal(VoyageDTO voyageDTO, Long id) {
        voyageDTO.setId(id);
        return update(voyageDTO);
    }

    @GetMapping
    public List<VoyageDTO> getAll(){
        log.debug("REST request to get All");
        return voyageRepository.findAll().stream().map(voyage -> {
            return voyageMapper.toDto(voyage);
        }).toList();
    }

}
