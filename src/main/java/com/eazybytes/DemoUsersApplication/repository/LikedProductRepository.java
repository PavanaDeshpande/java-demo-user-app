package com.eazybytes.DemoUsersApplication.repository;

import com.eazybytes.DemoUsersApplication.model.LikedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedProductRepository extends JpaRepository<LikedProduct, Long> {
    List<LikedProduct> findByUserName(String userName);
}
