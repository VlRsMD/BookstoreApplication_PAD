package com.example.Orders.orders;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    @Transactional(timeout = 500)
    public List<Orders> getOrders() {
        return ordersService.getOrders();
    }

    @PostMapping()
    @Transactional(timeout = 500)
    public void addOrder(@RequestBody Orders order) {
        ordersService.addOrder(order);
    }

    @DeleteMapping("{orderId}")
    @Transactional(timeout = 500)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        ordersService.deleteOrder(orderId);
    }

    @PatchMapping("{orderId}")
    @Transactional(timeout = 500)
    public void changeAddress(
            @PathVariable("orderId") Long orderId,
            @RequestParam(required = false) String address) {
        ordersService.changeAddress(orderId, address);
    }
}
