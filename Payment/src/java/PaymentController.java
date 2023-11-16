package com.example.Payment.payment;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController implements PaymentControllerInterface {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    @GetMapping
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker1", fallbackMethod = "fallback1")
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @Override
    @PostMapping()
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker2", fallbackMethod = "fallback2")
    public void addPayment(@RequestBody Payment payment) {
        paymentService.addPayment(payment);
    }

    @Override
    @DeleteMapping("{paymentId}")
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker3", fallbackMethod = "fallback3")
    public void deletePayment(@PathVariable("paymentId") Long paymentId) {
        paymentService.deletePayment(paymentId);
    }

    @Override
    @PatchMapping("{paymentId}")
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker4", fallbackMethod = "fallback4")
    public void changePaymentMethod(
            @PathVariable("paymentId") Long paymentId,
            @RequestParam(required = false) String paymentMethod) {
        paymentService.changePaymentMethod(paymentId, paymentMethod);
    }

    private String fallback1(Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }

    private String fallback2(@RequestBody Payment payment, Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }

    private String fallback3(@PathVariable("paymentId") Long paymentId, Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }

    private String fallback4(@PathVariable("paymentId") Long paymentId,
                             @RequestParam(required = false) String paymentMethod, Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }
}
