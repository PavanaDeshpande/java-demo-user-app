package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.model.Order;
import com.eazybytes.DemoUsersApplication.service.OrderCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderCategoryController {
    private final OrderCategoryService orderCategoryService;

    public OrderCategoryController(OrderCategoryService orderCategoryService) {
        this.orderCategoryService = orderCategoryService;
    }

    //not the right way to do many to one
//    @PostMapping("/{orderId}/orderCategory")
//    public ResponseEntity<?> assigncategoryToOrder(@Valid @RequestBody OrderCategory orderCategory, @PathVariable Long orderId) {
//        Order orders = orderCategoryService.assigncategoryToOrder(orderCategory, orderId);
//
//        return ResponseEntity.ok(orders);
//    }

    //update will be same as create
    //to delete a category, set that specific category to null in orders table
    @PostMapping("/{orderId}/category/{categoryId}")
    public ResponseEntity<?> assigncategoryToOrder( @PathVariable Long orderId, @PathVariable Long categoryId) {
        Order orders = orderCategoryService.assigncategoryToOrder(orderId, categoryId);

        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{orderId}/category")
    public ResponseEntity<String> getCategoryForOrder(@Valid @PathVariable Long orderId) {
        String categoryname = orderCategoryService.getCategoryForOrder(orderId);
        return ResponseEntity.ok(categoryname);
    }
}
