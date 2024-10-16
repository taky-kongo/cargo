package ci.atosdigitalacademy.cargo.services.impl;

import ci.atosdigitalacademy.cargo.models.Payment;
import ci.atosdigitalacademy.cargo.repositories.PaymentRepository;
import ci.atosdigitalacademy.cargo.services.PaymentService;
import ci.atosdigitalacademy.cargo.services.TypePaymentService;
import ci.atosdigitalacademy.cargo.services.dto.PaymentDTO;
import ci.atosdigitalacademy.cargo.services.dto.TypePaymentDTO;
import ci.atosdigitalacademy.cargo.services.mapper.PaymentMapper;
import ci.atosdigitalacademy.cargo.services.mapping.PaymentMapping;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final TypePaymentService typePaymentService;

    @Override
    public PaymentDTO save(PaymentDTO paymentDTO) {
        log.debug("Request to save type payment {}", paymentDTO);
        Optional<TypePaymentDTO> typePayment = typePaymentService.findOne(paymentDTO.getTypePayment().getId());
        if (typePayment.isPresent()) {
            paymentDTO.setTypePayment(typePayment.get());
        } else {
            throw new IllegalArgumentException("Voyage not found");
        }
        Payment Payment = paymentMapper.toEntity(paymentDTO);
        Payment = paymentRepository.save(Payment);
        return paymentMapper.toDto(Payment);
    }

    @Override
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        log.debug("Request to save payment: {} with slug", paymentDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(paymentDTO.getAmount()));
        paymentDTO.setSlug(slug);
        return save(paymentDTO);
    }

    @Override
    public PaymentDTO update(PaymentDTO paymentDTO) {
        return findOne(paymentDTO.getId()).map(payment -> {
            payment.setPhoneNumber(paymentDTO.getPhoneNumber());
            payment.setAmount(paymentDTO.getAmount());
            payment.setDatePayment(paymentDTO.getDatePayment());
            return save(payment);
        }).orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    @Override
    public PaymentDTO update(PaymentDTO paymentDTO, Long id) {
        log.debug("Request to update payment: {} with id {}", paymentDTO, id);
        paymentDTO.setId(id);
        return update(paymentDTO);
    }

    @Override
    public PaymentDTO partialUpdate(PaymentDTO paymentDTO, Long id) {
        log.debug("Request to partial update payment by id : {}", id);
        return paymentRepository.findById(id).map(payment -> {
            PaymentMapping.partialUpdate(payment, paymentDTO);
            return payment;
        }).map(paymentRepository::save).map(paymentMapper::toDto).orElse(null);
    }

    @Override
    public List<PaymentDTO> findAll() {
        log.debug("Request to get all Payment");
        return paymentRepository.findAll().stream().map(payment -> {
            return paymentMapper.toDto(payment);
        }).toList();
    }

    @Override
    public Optional<PaymentDTO> findOne(Long id) {
        log.debug("Request to get Payment : {}",id);
        return paymentRepository.findById(id).map(payment -> {
            return paymentMapper.toDto(payment);
        });
    }

    @Override
    public Optional<PaymentDTO> findBySlug(String slug) {
        log.debug("Request to get Payment by slug ");
        return paymentRepository.findBySlug(slug).map(payment -> {
            return paymentMapper.toDto(payment);
        });
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Payment :{}", id);
        paymentRepository.deleteById(id);
    }
}
