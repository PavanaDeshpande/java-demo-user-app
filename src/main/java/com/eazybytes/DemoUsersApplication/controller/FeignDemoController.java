package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.client.LikedProductClient;
import com.eazybytes.DemoUsersApplication.model.LikedProduct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feign")
public class FeignDemoController {

    private final LikedProductClient client;

    public FeignDemoController(LikedProductClient client) {
        this.client = client;
    }

    @GetMapping("/{userName}")
    public List<LikedProduct> fetchWithFeign(@PathVariable String userName) {
        return client.getLikedProducts(userName);
    }

}

