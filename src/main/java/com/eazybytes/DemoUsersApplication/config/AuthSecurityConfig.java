package com.eazybytes.DemoUsersApplication.config;

import com.eazybytes.DemoUsersApplication.security.CustomAuthenticationProvider;
import com.eazybytes.DemoUsersApplication.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
public class AuthSecurityConfig {

    private final CustomAuthenticationProvider customAuthProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public AuthSecurityConfig(CustomAuthenticationProvider customAuthProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.customAuthProvider = customAuthProvider;
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(customAuthProvider));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                );
//        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        .httpBasic(Customizer.withDefaults());
//                .formLogin(Customizer.withDefaults()); // optional default login page for browser

        return http.build();
    }
}