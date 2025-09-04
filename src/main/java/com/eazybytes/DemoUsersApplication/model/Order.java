package com.eazybytes.DemoUsersApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = true) // foreign key column
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private OrderCategory category;

    @ManyToMany
    @JoinTable(
            name = "order_tags",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
//    @JsonBackReference
    @JsonIgnore
    private List<Tag> tags = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public OrderCategory getCategory() {
        return category;
    }

    public void setCategory(OrderCategory category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
