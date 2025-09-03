package com.eazybytes.DemoUsersApplication.service;
import com.eazybytes.DemoUsersApplication.exception.UserAlreadyExistsException;
import com.eazybytes.DemoUsersApplication.model.Role;
import com.eazybytes.DemoUsersApplication.model.User;
import com.eazybytes.DemoUsersApplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
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
    public Role getRoleForUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));;
        return user.getRole();
    }
    public User createUser(User user) {
//        public User createUser(User user, String roleName) {
        try {
            Role role = new Role();
            role.setRoleName(user.getRole().getRoleName());
            role.setUser(user);

            user.setRole(role);
            return userRepository.save(user);
        }
//        } catch (Exception e) {
//            throw new RuntimeException("not able to add "+e);
//        }
//            catch (DataIntegrityViolationException e) {
//                throw new IllegalArgumentException("Email already exists or constraint violated");
//            }
        catch (Exception e) {
            throw new UserAlreadyExistsException("Email exists, there is a conflict");
        }

        }

    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            Role role = user.getRole();
            role.setRoleName(updatedUser.getRole().getRoleName());
            user.setRole(role);
            role.setUser(user);
            return userRepository.save(user);
        });
//        Optional<User> optionalUser = userRepository.findById(id);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setName(updatedUser.getName());
//            user.setEmail(updatedUser.getEmail());
//            user.getRole().setRoleName(updatedUser.getRole().getRoleName());
//            return Optional.of(userRepository.save(user));
//        } else {
//            return Optional.empty();
//        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
