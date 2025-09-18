package com.eazybytes.DemoUsersApplication.service;

import com.eazybytes.DemoUsersApplication.dto.LoginRequest;
import com.eazybytes.DemoUsersApplication.model.AuthRole;
import com.eazybytes.DemoUsersApplication.model.AuthUser;
import com.eazybytes.DemoUsersApplication.repository.AuthRoleRepository;
import com.eazybytes.DemoUsersApplication.repository.AuthUserRepository;
import com.eazybytes.DemoUsersApplication.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthUserRepository userRepository;

    @Autowired
    private AuthRoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthUser registerUser(String username, String password, String roleName) {
        AuthUser user = new AuthUser();
        user.setUsername(username);
        user.setPassword(password); // plain-text password storage

        AuthUser savedUser = userRepository.save(user);

        AuthRole role = new AuthRole();
        role.setRoleName(roleName);
        role.setUser(savedUser);
        savedUser.setRole(role);

        roleRepository.save(role);

        return savedUser;
    }

    public AuthUser registerUserUsingBCrypt(String username, String password, String roleName) {
        AuthUser user = new AuthUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // hashed password

        AuthUser savedUser = userRepository.save(user);

        AuthRole role = new AuthRole();
        role.setRoleName(roleName);
        role.setUser(savedUser);
        savedUser.setRole(role);

        roleRepository.save(role);

        return savedUser;
    }

    public boolean loginUser(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return auth != null && auth.isAuthenticated();
    }

    public boolean loginUserUsingBCrypt(LoginRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .map(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .orElse(false);

    }

    public String loginJwt(LoginRequest request) {
        AuthUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtils.generateJwtToken(user.getUsername());
    }

    public boolean loginUserUsingJSessionId(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}
