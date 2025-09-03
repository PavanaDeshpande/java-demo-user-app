package com.eazybytes.DemoUsersApplication.repository;

import com.eazybytes.DemoUsersApplication.model.Order;
import com.eazybytes.DemoUsersApplication.model.OrderCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCategoryRepository extends JpaRepository<OrderCategory, Long> {
}
