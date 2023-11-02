package com.example.Orders.orders;

import com.example.Orders.orders.Orders;
import com.example.Orders.orders.OrdersRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    public void addOrder(Orders order) {
        ordersRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        boolean exists = ordersRepository.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException("Order with ID " + orderId + " does not exist. Unable to delete a nonexistent order.");
        }
        ordersRepository.deleteById(orderId);
    }

    @Transactional
    @Cacheable(value = "order", key = "#id")
    public void changeAddress(Long orderId, String address) {
        if (address== null || address.length() == 0) {
            return;
        }
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Order with ID " + orderId + " does not exist. Unable to change the address of a nonexistent order.");
                });
        if(!Objects.equals(order.getAddress(), address)) {
            order.setAddress(address);
        }
    }
}
