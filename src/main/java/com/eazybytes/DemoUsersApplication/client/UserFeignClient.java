package com.eazybytes.DemoUsersApplication.client;

import com.eazybytes.DemoUsersApplication.config.UserFeignConfig;
import com.eazybytes.DemoUsersApplication.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/users", configuration = UserFeignConfig.class)
public interface UserFeignClient {

    @GetMapping("/{id}")
    User getUserById(@PathVariable("id") Long id);
}
