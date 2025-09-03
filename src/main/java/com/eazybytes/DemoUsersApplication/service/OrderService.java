package com.eazybytes.DemoUsersApplication.service;

import com.eazybytes.DemoUsersApplication.model.Order;
import com.eazybytes.DemoUsersApplication.model.User;
import com.eazybytes.DemoUsersApplication.repository.OrderRepository;
import com.eazybytes.DemoUsersApplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {

        this.orderRepository = orderRepository;
        this.userRepository=userRepository;
    }
    public Order createOrder(Order order, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        order.setUser(user);
        return orderRepository.save(order);
    }
    public List<Order> getAllOrdersForAnUser(Long userId){
        return  orderRepository.findByUserId(userId);
    }
    public Order updateOrderByOrderId(Order updatedOrder, Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + orderId));
            order.setProductName(updatedOrder.getProductName());
        return orderRepository.save(order);
    }
    public void deleteOrderByOrderId(Long orderId) {
        orderRepository.deleteById(orderId);
    }
    public void deleteAllOrdersForAnUser(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        orderRepository.deleteAll(orders);
    }
}
