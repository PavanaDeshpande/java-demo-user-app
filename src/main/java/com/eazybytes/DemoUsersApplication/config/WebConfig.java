package com.eazybytes.DemoUsersApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // allow all paths
                .allowedOrigins("http://localhost:3000") // allow specific frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // allowed HTTP methods
                .allowedHeaders("*"); // allow all headers
    }
}
