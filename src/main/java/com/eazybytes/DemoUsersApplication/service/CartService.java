package com.eazybytes.DemoUsersApplication.service;


import com.eazybytes.DemoUsersApplication.model.Cart;
import com.eazybytes.DemoUsersApplication.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartsByProduct(String productName) {
        return cartRepository.findByProductName(productName);
    }

    public List<Cart> getCartsWithMinQuantity(int minQuantity) {
        return cartRepository.findCartsWithMinQuantity(minQuantity);
    }
    public List<Cart> getCartsByUserName(String userName) {
        return cartRepository.findByUserName(userName);
    }

    public List<Cart> getExpensiveCarts(int minQuantity) {
        return cartRepository.findExpensiveCarts(minQuantity);
    }
    public
    int updateCartQuantity(String userName, String productName, int quantity) {
        return cartRepository.updateCartQuantity(userName, productName, quantity);
    }

}
