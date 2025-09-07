package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.model.Payment;
import com.eazybytes.DemoUsersApplication.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/by-amount")
    public List<Payment> getPaymentsSortedByAmount() {
        return paymentService.getPaymentsSortedByAmount();
    }

    @GetMapping("/by-status/{status}")
    public List<Payment> getPaymentsByStatus(@PathVariable String status) {
        return paymentService.getPaymentsByStatus(status);
    }

    //dynamic sorting
    @GetMapping
    public List<Payment> getPayments(
            @RequestParam(defaultValue = "paymentDate") String columnName,
            @RequestParam(defaultValue = "asc") String direction) {

        return paymentService.getPaymentsSorted(columnName, direction);
    }
    @GetMapping("/status")
    public List<Payment> getPaymentsAndFilterByStatus(@RequestParam String status,
            @RequestParam(defaultValue = "paymentDate") String columnName,
            @RequestParam(defaultValue = "asc") String direction) {

        return paymentService.getPaymentsAndFilterByStatus(status, columnName, direction);
    }

    //pagination
    @GetMapping("/paged")
    public Page<Payment> getPagedPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return paymentService.getPayments(page, size);
    }

    //pagination and filter
    @GetMapping("/paged/filter")
    public Page<Payment> getFilteredPayments(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return paymentService.getPaymentsByStatus(status, page, size);
    }

    //pagination and filter and sorting
    @GetMapping("/paged/filterSort")
    public Page<Payment> getFilteredPaymentsAndSort(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam String columnName,
            @RequestParam String direction) {

        return paymentService.getFilteredPaymentsAndSort(status, page, size, columnName, direction);
    }
}
