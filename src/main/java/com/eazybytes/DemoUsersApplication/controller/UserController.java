package com.eazybytes.DemoUsersApplication.controller;
import com.eazybytes.DemoUsersApplication.model.Role;
import com.eazybytes.DemoUsersApplication.model.User;
import com.eazybytes.DemoUsersApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.springframework.core.env.Environment;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private static final Logger logger = Logger.getLogger(OrderController.class.getName());

    @Value("${app.name}")
    private String appName;


    private final Environment environment;

    public UserController(UserService userService, Environment environment) {
        this.userService = userService;
        this.environment = environment;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws IOException {
        System.out.println("App Name: " + appName);
        System.out.println(environment.getProperty("welcome.message", "Default Welcome Message"));
        FileHandler fileHandler = new FileHandler("app.log", true);
        fileHandler.setFormatter(new SimpleFormatter()); // Important!
        fileHandler.setLevel(Level.ALL); // Accept all levels

        // Add handler to logger
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL); // Ensure logger itself accepts all levels

        // Log message
        logger.info("Order fetched successfully: " + id);

        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/role/{id}")
    public Role getRoleForUser(@PathVariable Long id){
        return userService.getRoleForUser(id);
    }
    @PostMapping
//    public ResponseEntity<?> createUser(@Valid @RequestBody User user, @RequestParam String roleName) {
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
//        User savedUser = userService.createUser(user, roleName);
        User savedUser = userService.createUser(user);

        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        return userService.updateUser(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getUserByEmailAndname(@RequestParam String email, @RequestParam String name) {
        List<User> users = userService.getUserByEmailAndname(email, name);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 if no match
        }
        return ResponseEntity.ok(users);
    }

}
