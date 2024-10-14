package ci.atosdigitalacademy.cargo.services.mapping;

import ci.atosdigitalacademy.cargo.models.Payment;
import ci.atosdigitalacademy.cargo.services.dto.PaymentDTO;

public final class PaymentMapping {

    private PaymentMapping() {}

    public static void partialUpdate(Payment payment, PaymentDTO paymentDTO) {
        if (paymentDTO.getDatePayment() != null) {
            payment.setDatePayment(paymentDTO.getDatePayment());
        }
        if (paymentDTO.getAmount() != null) {
            payment.setAmount(paymentDTO.getAmount());
        }
    }
}
