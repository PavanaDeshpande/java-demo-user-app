package com.eazybytes.DemoUsersApplication.service;

import com.eazybytes.DemoUsersApplication.model.Order;
import com.eazybytes.DemoUsersApplication.model.OrderCategory;
import com.eazybytes.DemoUsersApplication.model.User;
import com.eazybytes.DemoUsersApplication.repository.OrderCategoryRepository;
import com.eazybytes.DemoUsersApplication.repository.OrderRepository;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderCategoryService {
    private final OrderCategoryRepository orderCategoryRepository;
    private final OrderRepository orderRepository;

    public OrderCategoryService(OrderCategoryRepository orderCategoryRepository, OrderRepository orderRepository){
        this.orderCategoryRepository=orderCategoryRepository;
        this.orderRepository=orderRepository;
    }

    //not the right way to do many to one
//    public Order assigncategoryToOrder(OrderCategory orderCategory, Long orderId) {
//        Order order = orderRepository.findById(orderId)
//                    .orElseThrow(() -> new RuntimeException("User not found with id " + orderId));
//        order.setCategory(orderCategory);
//        return orderRepository.save(order);
//    }

    public Order assigncategoryToOrder(Long orderId, Long categoryId) {
        Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("User not found with id " + orderId));
        OrderCategory category= orderCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + categoryId));

        order.setCategory(category);
        return orderRepository.save(order);
    }
    public  String getCategoryForOrder(Long orderId){
       Order order= orderRepository.findById(orderId)
               .orElseThrow(() -> new RuntimeException("User not found with id " + orderId));

        return  order.getCategory().getName();
    }
}
