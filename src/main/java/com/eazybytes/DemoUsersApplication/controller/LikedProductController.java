package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.model.LikedProduct;
import com.eazybytes.DemoUsersApplication.repository.LikedProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/liked-products")
public class LikedProductController {

    private final LikedProductRepository repository;

    public LikedProductController(LikedProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{userName}")
    public List<LikedProduct> getLikedProducts(@PathVariable String userName) {
        return repository.findByUserName(userName);
    }

}
