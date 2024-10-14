package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.PaymentDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

        PaymentDTO save(PaymentDTO paymentDTO);

        PaymentDTO savePayment(PaymentDTO paymentDTO);

        PaymentDTO update(PaymentDTO paymentDTO);

        PaymentDTO update(PaymentDTO paymentDTO,Long id);

        PaymentDTO partialUpdate(PaymentDTO paymentDTO, Long id);

        List<PaymentDTO> findAll();

        Optional<PaymentDTO> findOne(Long id);

        Optional<PaymentDTO> findBySlug(String slug);

        void delete(Long id);
}
