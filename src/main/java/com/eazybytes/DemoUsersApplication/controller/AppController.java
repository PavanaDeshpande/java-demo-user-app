package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.config.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final AppProperties appProperties;

    public AppController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/app-info")
    public String getAppInfo() {
        return "App: " + appProperties.getName() +
                " | Version: " + appProperties.getVersion() +
                " | Timeout: " + appProperties.getTimeout();
    }
}
