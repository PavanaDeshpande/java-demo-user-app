package com.eazybytes.DemoUsersApplication.repository;
import com.eazybytes.DemoUsersApplication.model.Order;

import com.eazybytes.DemoUsersApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}