package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.dto.LoginRequest;
import com.eazybytes.DemoUsersApplication.dto.LoginResponse;
import com.eazybytes.DemoUsersApplication.model.AuthRole;
import com.eazybytes.DemoUsersApplication.model.AuthUser;
import com.eazybytes.DemoUsersApplication.repository.AuthRoleRepository;
import com.eazybytes.DemoUsersApplication.repository.AuthUserRepository;
import com.eazybytes.DemoUsersApplication.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String roleName) {
        authService.registerUser(username, password, roleName);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        boolean success = authService.loginUser(request);
        return success ? "Login successful for user: " + request.getUsername() : "Invalid credentials";
    }

    @PostMapping("/registerUsingBCrypt")
    public String registerUserUsingBCrypt(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String roleName) {
        authService.registerUserUsingBCrypt(username, password, roleName);
        return "User registered successfully!";
    }
    @PostMapping("/loginUsingBCrypt")
    public String loginUsingBCrypt(@RequestBody LoginRequest request) {
        boolean success = authService.loginUserUsingBCrypt(request);
        return success ? "Login successful for user: " + request.getUsername() : "Invalid credentials";
    }

    @PostMapping("/loginJwt")
    public ResponseEntity<LoginResponse> loginJwt(@RequestBody LoginRequest request) {
        String token = authService.loginJwt(request);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/loginUsingJSessionId")
    public String loginUserUsingJSessionId(@RequestParam String username,
                                           @RequestParam String password,
                                           HttpServletRequest request) {

        boolean success = authService.loginUserUsingJSessionId(username, password);

        if (success) {
            // Create Authentication and put into SecurityContext
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(auth);
            SecurityContextHolder.setContext(securityContext);

            // Store it in HttpSession (Spring Security will reuse it)
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            return "Login successful for user: " + username;
        } else {
            return "Invalid credentials";
        }
    }


}