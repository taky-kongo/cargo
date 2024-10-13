package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.CompanyDTO;
import ci.atosdigitalacademy.cargo.services.dto.TypePaymentDTO;

import java.util.List;
import java.util.Optional;

public interface TypePaymentService {

        TypePaymentDTO saveTypePayment(TypePaymentDTO typePaymentDTO);
        TypePaymentDTO updateTypePayment(TypePaymentDTO typePaymentDTO);
        TypePaymentDTO updateTypePayment(TypePaymentDTO typePaymentDTO,Long id);
        List<TypePaymentDTO> findAllTypePayment();

        Optional<TypePaymentDTO> findOne(Long id);

        Optional<TypePaymentDTO> findBySlug(String slug);
        void deleteTypePayment(Long id);

        TypePaymentDTO partialUpdate(TypePaymentDTO typePayment, Long id);
}
