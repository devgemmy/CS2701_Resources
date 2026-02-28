package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;

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
        return ((List<User>) userRepository.findAll());
    }

    // POST create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}