package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.model.Cart;
import com.eazybytes.DemoUsersApplication.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/product/{productName}")
    public List<Cart> getCartsByProduct(@PathVariable String productName) {
        return cartService.getCartsByProduct(productName);
    }

    @GetMapping("/minQuantity/{minQuantity}")
    public List<Cart> getCartsWithMinQuantity(@PathVariable int minQuantity) {
        return cartService.getCartsWithMinQuantity(minQuantity);
    }
    // API for @NamedQuery
    @GetMapping("/user/{userName}")
    public List<Cart> getCartsByUserName(@PathVariable String userName) {
        return cartService.getCartsByUserName(userName);
    }

    // API for @NamedNativeQuery
    @GetMapping("/expensive/{minQuantity}")
    public List<Cart> getExpensiveCarts(@PathVariable int minQuantity) {
        return cartService.getExpensiveCarts(minQuantity);
    }

    @PutMapping("/updateQuantity")
    public String updateCartQuantity(
            @RequestParam String userName,
            @RequestParam String productName,
            @RequestParam int quantity) {

        int rows = cartService.updateCartQuantity(userName, productName, quantity);
        return rows > 0 ? "Updated successfully" : "No record found to update";
    }
}
