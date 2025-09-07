package com.eazybytes.DemoUsersApplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
@NamedQuery(
        name = "Cart.findByUserName",
        query = "SELECT c FROM Cart c WHERE c.userName = :userName"
)
@NamedNativeQuery(
        name = "Cart.findExpensiveCarts",
        query = "SELECT * FROM carts WHERE quantity > :minQuantity",
        resultClass = Cart.class
)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String productName;

    private int quantity;

    public Cart() {}

    public Cart(String userName, String productName, int quantity) {
        this.userName = userName;
        this.productName = productName;
        this.quantity = quantity;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
