package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.model.LikedProduct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/webclient")
public class WebClientController {

    private final WebClient webClient;

    public WebClientController(WebClient webClient) {
        this.webClient = webClient;
    }

    // GET liked products for a user
    @GetMapping("/{userName}")
    public List<LikedProduct> getLikedProducts(@PathVariable String userName) {
        return webClient.get()
                .uri("/{userName}", userName)               // append userName to baseUrl
                .retrieve()                                 // perform the request
                .bodyToFlux(LikedProduct.class)             // parse JSON to Flux<LikedProduct>
                .collectList()                              // convert Flux to List
                .block();                                   // block to wait for response
    }
}
