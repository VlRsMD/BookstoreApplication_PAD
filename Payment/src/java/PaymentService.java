package com.example.Payment.payment;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public void addPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public void deletePayment(Long paymentId) {
        boolean exists = paymentRepository.existsById(paymentId);
        if (!exists) {
            throw new IllegalStateException("Payment with ID " + paymentId + " does not exist. Unable to delete a nonexistent payment.");
        }
        paymentRepository.deleteById(paymentId);
    }

    @Transactional
    @Cacheable(value = "payment", key = "#id")
    public void changePaymentMethod(Long paymentId, String paymentMethod) {
        if (paymentMethod == null || paymentMethod.length() == 0) {
            return;
        }
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Payment with ID " + paymentId + " does not exist. Unable to change the payment method of a nonexistent payment.");
                });
        if(!Objects.equals(payment.getPaymentMethod(), paymentMethod)) {
            payment.setPaymentMethod(paymentMethod);
        }
    }
}
