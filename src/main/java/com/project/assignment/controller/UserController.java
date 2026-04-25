package com.project.assignment.controller;

import com.project.assignment.entity.User;
import com.project.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    private User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

}
