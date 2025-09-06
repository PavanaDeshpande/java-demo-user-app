package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.client.UserFeignClient;
import com.eazybytes.DemoUsersApplication.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign-users")
public class UserFeignController {

    private final UserFeignClient userFeignClient;

    public UserFeignController(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userFeignClient.getUserById(id);
    }
}
