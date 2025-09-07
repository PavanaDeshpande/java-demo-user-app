package com.eazybytes.DemoUsersApplication.service;

import java.util.List;
import com.eazybytes.DemoUsersApplication.model.Payment;
import com.eazybytes.DemoUsersApplication.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository=paymentRepository;
    }

    // Static sorting: by amount (desc)
    public List<Payment> getPaymentsSortedByAmount() {
        return paymentRepository.findAllByOrderByAmountDesc();
    }

    // Static sorting: by status + amount (desc)
    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findByStatusOrderByAmountDesc(status);
    }

    // Dynamic sorting method
    public List<Payment> getPaymentsSorted(String columnName, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(columnName).descending() :
                Sort.by(columnName).ascending();

        return paymentRepository.findAll(sort);
    }

    // Dynamic sorting method
    public List<Payment> getPaymentsAndFilterByStatus(String status, String columnName, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(columnName).descending() :
                Sort.by(columnName).ascending();

        return paymentRepository.findByStatus(status, sort);
    }

    // Simple pagination: only page and size
    public Page<Payment> getPayments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return paymentRepository.findAll(pageable);
    }

    //pagination and filter
    public Page<Payment> getPaymentsByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return paymentRepository.findByStatus(status, pageable);
    }

    //pagination and filter and sorting
    public Page<Payment> getFilteredPaymentsAndSort(String status, int page, int size, String columnName, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(columnName).descending() :
                Sort.by(columnName).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return paymentRepository.findByStatus(status, pageable);
    }
}

