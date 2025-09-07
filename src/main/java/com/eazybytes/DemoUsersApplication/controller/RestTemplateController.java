package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.model.LikedProduct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/resttemplate")
public class RestTemplateController {

    private final RestTemplate restTemplate;

    public RestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{userName}")
    public LikedProduct[] getLikedProducts(@PathVariable String userName) {
        String url = "http://localhost:8080/liked-products/" + userName;
        return restTemplate.getForObject(url, LikedProduct[].class);
    }
}
