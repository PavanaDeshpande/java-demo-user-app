package com.eazybytes.DemoUsersApplication.client;

import com.eazybytes.DemoUsersApplication.model.LikedProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "likedProductsClient", url = "http://localhost:8080/liked-products")
public interface LikedProductClient {

    @GetMapping("/{userName}")
    List<LikedProduct> getLikedProducts(@PathVariable("userName") String userName);

}

