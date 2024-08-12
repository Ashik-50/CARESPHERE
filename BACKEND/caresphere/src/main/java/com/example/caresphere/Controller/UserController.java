package com.example.caresphere.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.caresphere.Model.User;
import com.example.caresphere.Service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.usernameExists(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success", false, "message", "Username already exists"));
        }
        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success", false, "message", "Email already exists"));
        }

        try {
            User newUser = userService.createUser(user);
            return ResponseEntity.ok(Map.of("success", true, "message", "Registration successful", "user", newUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", "An error occurred during registration"));
        }
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkLogin(@RequestBody User login) {
        User existingLogin = userService.findByUsername(login.getUsername());
        if (existingLogin != null && existingLogin.getPassword().equals(login.getPassword())) {
            String role = existingLogin.getRole().toLowerCase();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login successful!");

            switch (role) {
                case "admin":
                    response.put("isAdmin", true);
                    response.put("role", "admin");
                    break;
                case "doctor":
                    response.put("isDoctor", true);
                    response.put("role", "doctor");
                    break;
                case "user":
                    response.put("isUser", true);
                    response.put("role", "user");
                    break;
                default:
                    response.put("role", "unknown");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("success", false, "message", "Unknown role"));
            }
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false, "message", "Invalid username or password"));
        }
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/count")
    public long getUserCount() {
        return userService.countUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.ok(Map.of("success", true, "message", "User deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "User not found"));
        }
    }
}
