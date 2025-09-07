package com.eazybytes.DemoUsersApplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "liked_products")
public class LikedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String productName;

    public LikedProduct() {}

    public LikedProduct(String userName, String productName) {
        this.userName = userName;
        this.productName = productName;
    }

    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
}
