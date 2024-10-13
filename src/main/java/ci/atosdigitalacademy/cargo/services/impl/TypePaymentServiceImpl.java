package ci.atosdigitalacademy.cargo.services.impl;

import ci.atosdigitalacademy.cargo.models.TypePayment;
import ci.atosdigitalacademy.cargo.repositories.TypePaymentRepository;
import ci.atosdigitalacademy.cargo.services.TypePaymentService;
import ci.atosdigitalacademy.cargo.services.dto.TypePaymentDTO;
import ci.atosdigitalacademy.cargo.services.mapper.TypePaymentMapper;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TypePaymentServiceImpl implements TypePaymentService {

    private final TypePaymentMapper typePaymentMapper;
    private final TypePaymentRepository typePaymentRepository;

    @Override
    public TypePaymentDTO save(TypePaymentDTO typePaymentDTO) {
        log.debug("Request to save type payment {}", typePaymentDTO);
        TypePayment typePayment= typePaymentMapper.toEntity(typePaymentDTO);
        typePayment = typePaymentRepository.save(typePayment);
        return typePaymentMapper.toDto(typePayment);
    }

    @Override
    public TypePaymentDTO saveTypePayment(TypePaymentDTO typePaymentDTO) {
        log.debug("Request to update: {} ", typePaymentDTO);
        log.debug("Request to save type payment: {} with slug", typePaymentDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(typePaymentDTO.getLabel()));
        typePaymentDTO.setSlug(slug);
        return save(typePaymentDTO);
    }
    @Override
    public TypePaymentDTO updateTypePayment(TypePaymentDTO typePaymentDTO) {
        return findOne(typePaymentDTO.getId()).map(typePayment -> {
            typePayment.setLabel(typePaymentDTO.getLabel());
            return save(typePayment);
        }).orElseThrow(() -> new IllegalArgumentException("Type payment not found"));
    }
    @Override
    public TypePaymentDTO updateTypePayment(TypePaymentDTO typePaymentDTO, Long id) {
        log.debug("Request to update: {} with id {}",typePaymentDTO, id);
        typePaymentDTO.setId(id);
        return updateTypePayment(typePaymentDTO);
    }
    @Override
    public List<TypePaymentDTO> findAllTypePayment() {
        log.debug("Request to get all typePayment");
        return typePaymentRepository.findAll().stream().map(typePayment -> {
            return typePaymentMapper.toDto(typePayment);
        }).toList();
    }
    @Override
    public Optional<TypePaymentDTO> findOne(Long id) {
        log.debug("Request to get TypePayment : {}",id);
        return typePaymentRepository.findById(id).map(typePayment -> {
            return typePaymentMapper.toDto(typePayment);
        });
    }
    @Override
    public Optional<TypePaymentDTO> findBySlug(String slug) {
        log.debug("Request to get TypePayment by slug ");
        return typePaymentRepository.findTypePaymentBySlug(slug).map(typePayment -> {
            return typePaymentMapper.toDto(typePayment);
        });
    }
    @Override
    public void deleteTypePayment(Long id) {
        log.debug("Request to delete typePayment :{}", id);
        typePaymentRepository.deleteById(id);
    }
}
