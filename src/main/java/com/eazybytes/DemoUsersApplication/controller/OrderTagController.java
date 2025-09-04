package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.model.Order;
import com.eazybytes.DemoUsersApplication.model.Tag;
import com.eazybytes.DemoUsersApplication.service.OrderTagService;
import com.eazybytes.DemoUsersApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")  // allow React frontend
public class OrderTagController {
    private final OrderTagService orderTagService;

    public OrderTagController(OrderTagService orderTagService){
        this.orderTagService = orderTagService;
    }

    @PostMapping("/{orderId}/tag")
    public ResponseEntity<?> assignTagToOrder(@Valid @RequestBody Map<String, String> tag, @PathVariable Long orderId) {
        Order orders = orderTagService.assignTagToOrder(tag, orderId);
        return ResponseEntity.ok(orders);
    }
    @PutMapping("/{orderId}/tag")
    public ResponseEntity<?> replaceTagToOrder(@Valid @RequestBody Map<String, String> tag, @PathVariable Long orderId) {
        Order orders = orderTagService.replaceTagToOrder(tag, orderId);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{orderId}/tag")
    public List<Tag> getAllTagsForAnOrder(@Valid @PathVariable Long orderId) {

        return orderTagService.getAllTagsForAnOrder(orderId);
    }
    @GetMapping("/tag/{tagId}")
    @ResponseBody
    public List<Order> getAllOrderForATag(@Valid @PathVariable Long tagId) {

        return orderTagService.getAllOrderForATag(tagId);
    }
}
