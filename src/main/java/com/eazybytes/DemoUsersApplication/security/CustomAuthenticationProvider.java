package com.eazybytes.DemoUsersApplication.security;

import com.eazybytes.DemoUsersApplication.model.AuthUser;
import com.eazybytes.DemoUsersApplication.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        return userRepository.findByUsername(username)
//                .filter(user -> user.getPassword().equals(password)) //without BCrypt
                .filter(user -> passwordEncoder.matches(password, user.getPassword())) //with BCrypt
                .map(user -> new UsernamePasswordAuthenticationToken(
                        username,
                        password,
                        Collections.singleton(new SimpleGrantedAuthority(user.getRole().getRoleName()))
                ))
                .orElse(null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}