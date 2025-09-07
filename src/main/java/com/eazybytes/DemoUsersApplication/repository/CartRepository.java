package com.eazybytes.DemoUsersApplication.repository;

import com.eazybytes.DemoUsersApplication.model.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Custom JPQL query
    @Query("SELECT c FROM Cart c WHERE c.productName = :productName")
    List<Cart> findByProductName(String productName);

    // Custom native SQL query
    @Query(value = "SELECT * FROM carts WHERE quantity > :minQuantity", nativeQuery = true)
    List<Cart> findCartsWithMinQuantity(int minQuantity);

    // Use @NamedQuery (JPQL)
    List<Cart> findByUserName(String userName);

    // Use @NamedNativeQuery (SQL)
    List<Cart> findExpensiveCarts(int minQuantity);

    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.quantity = :quantity WHERE c.userName = :userName AND c.productName = :productName")
    int updateCartQuantity(String userName, String productName, int quantity);
}
