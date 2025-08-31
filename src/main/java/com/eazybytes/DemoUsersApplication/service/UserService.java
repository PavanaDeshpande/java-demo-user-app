package com.eazybytes.DemoUsersApplication.service;
import com.eazybytes.DemoUsersApplication.model.Role;
import com.eazybytes.DemoUsersApplication.model.User;
import com.eazybytes.DemoUsersApplication.repository.RoleRepository;
import com.eazybytes.DemoUsersApplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;

//    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
//        this.roleRepository=roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getUserByEmailAndname(String email, String name){
        return  userRepository.findByEmailAndName(email, name);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

//    public Role assignRoleToUser(Long userId, String roleName) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Role role = new Role();
//        role.setRoleName(roleName);
//        role.setUser(user);
//
//        user.setRole(role);
//
//        return roleRepository.save(role);
//    }
//
//    public Role getRoleForUser(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow();
//        return user.getRole();
//    }
}
