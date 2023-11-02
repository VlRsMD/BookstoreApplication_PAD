package com.example.Payment.payment;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @Transactional(timeout = 500)
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @PostMapping()
    @Transactional(timeout = 500)
    public void addPayment(@RequestBody Payment payment) {
        paymentService.addPayment(payment);
    }

    @DeleteMapping("{paymentId}")
    @Transactional(timeout = 500)
    public void deletePayment(@PathVariable("paymentId") Long paymentId) {
        paymentService.deletePayment(paymentId);
    }

    @PatchMapping("{paymentId}")
    @Transactional(timeout = 500)
    public void changePaymentMethod(
            @PathVariable("paymentId") Long paymentId,
            @RequestParam(required = false) String paymentMethod) {
        paymentService.changePaymentMethod(paymentId, paymentMethod);
    }
}
