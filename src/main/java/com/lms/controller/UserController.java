package com.lms.controller;

import com.lms.model.User;
import com.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        return userRepo.findByEmailAndPassword(email, password);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    @PutMapping("/{id}/password")
    public User changePassword(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(body.get("password"));
            return userRepo.save(user);
        }
        return null;
    }
}
