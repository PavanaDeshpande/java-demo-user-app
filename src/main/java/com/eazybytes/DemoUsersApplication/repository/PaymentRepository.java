package com.eazybytes.DemoUsersApplication.repository;

import com.eazybytes.DemoUsersApplication.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Static sorting: always sort by amount descending
    List<Payment> findAllByOrderByAmountDesc();

    // Static sorting: filter + sort
    List<Payment> findByStatusOrderByAmountDesc(String status);

    //Dynamic sort and filter
    List<Payment> findByStatus(String status, Sort sort);

    // Filter by status + pagination
    Page<Payment> findByStatus(String status, Pageable pageable);
}
