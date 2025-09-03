package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.model.Order;
import com.eazybytes.DemoUsersApplication.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/{userId}/orders")
    public ResponseEntity<?> createOrder(@Valid @RequestBody Order order, @PathVariable Long userId) {
        Order orders = orderService.createOrder(order, userId);

        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{userId}/orders")
    public List<Order> getAllOrdersForAnUser(@Valid @PathVariable Long userId) {

        return orderService.getAllOrdersForAnUser(userId);
    }
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<?> updateOrderByOrderId(@Valid @RequestBody Order order, @PathVariable Long orderId) {
        Order orders = orderService.updateOrderByOrderId(order, orderId);

        return ResponseEntity.ok(orders);
    }
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrderByOrderId(@PathVariable Long orderId) {
        orderService.deleteOrderByOrderId(orderId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{userId}/orders")
    public ResponseEntity<Void> deleteAllOrdersForAnUser(@PathVariable Long userId) {
        orderService.deleteAllOrdersForAnUser(userId);
        return ResponseEntity.noContent().build();
    }
}
