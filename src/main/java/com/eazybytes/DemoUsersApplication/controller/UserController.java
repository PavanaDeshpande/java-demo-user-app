package com.eazybytes.DemoUsersApplication.controller;
import com.eazybytes.DemoUsersApplication.model.Role;
import com.eazybytes.DemoUsersApplication.model.User;
import com.eazybytes.DemoUsersApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
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
//    @PostMapping("/{userId}/role")
//    public ResponseEntity<Role> assignRole(@PathVariable Long userId, @RequestBody Role role) {
//        Role savedRole = userService.assignRoleToUser(userId, role.getRoleName());
//        return ResponseEntity.ok(savedRole);
//    }
//
//    @GetMapping("/{userId}/role")
//    public ResponseEntity<Role> getRole(@PathVariable Long userId) {
//        return ResponseEntity.ok(userService.getRoleForUser(userId));
//    }

}
