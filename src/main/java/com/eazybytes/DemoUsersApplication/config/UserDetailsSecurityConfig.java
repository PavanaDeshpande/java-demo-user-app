package com.eazybytes.DemoUsersApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserDetailsSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChainForUser(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // login/register APIs are public
                        .anyRequest().authenticated() // everything else requires login
                )
                .formLogin(Customizer.withDefaults()); // default login form

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails rubyUser = User.withDefaultPasswordEncoder()
                .username("ruby")
                .password("password123")
                .roles("USER")
                .build();
        UserDetails adminUser = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(rubyUser, adminUser);
//        return new InMemoryUserDetailsManager(user);
    }
}
