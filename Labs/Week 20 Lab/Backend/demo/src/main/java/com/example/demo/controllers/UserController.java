package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // POST create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}