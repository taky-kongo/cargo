package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.TypePaymentDTO;

import java.util.List;
import java.util.Optional;

public interface TypePaymentService {

        TypePaymentDTO save(TypePaymentDTO typePaymentDTO);

        TypePaymentDTO saveTypePayment(TypePaymentDTO typePaymentDTO);

        TypePaymentDTO updateTypePayment(TypePaymentDTO typePaymentDTO);

        TypePaymentDTO updateTypePayment(TypePaymentDTO typePaymentDTO,Long id);

        List<TypePaymentDTO> findAllTypePayment();

        Optional<TypePaymentDTO> findOne(Long id);

        Optional<TypePaymentDTO> findBySlug(String slug);

        void deleteTypePayment(Long id);
}
