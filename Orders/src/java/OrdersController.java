package com.example.Orders.orders;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController implements OrdersControllerInterface {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    @GetMapping
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker1", fallbackMethod = "fallback1")
    public List<Orders> getOrders() {
        return ordersService.getOrders();
    }

    @Override
    @PostMapping()
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker2", fallbackMethod = "fallback2")
    public void addOrder(@RequestBody Orders order) {
        ordersService.addOrder(order);
    }

    @Override
    @DeleteMapping("{orderId}")
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker3", fallbackMethod = "fallback3")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        ordersService.deleteOrder(orderId);
    }

    @Override
    @PatchMapping("{orderId}")
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker4", fallbackMethod = "fallback4")
    public void changeAddress(
            @PathVariable("orderId") Long orderId,
            @RequestParam(required = false) String address) {
        ordersService.changeAddress(orderId, address);
    }

    private String fallback1(Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }

    private String fallback2(@RequestBody Orders order, Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }

    private String fallback3(@PathVariable("orderId") Long orderId, Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }

    private String fallback4(@PathVariable("orderId") Long orderId,
                             @RequestParam(required = false) String address, Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }
}
