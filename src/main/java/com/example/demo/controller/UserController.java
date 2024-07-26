package com.example.demo.controller;


import com.example.demo.model.Users;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/get-users/{id}")
    public Users getUserById(@PathVariable Integer id) {

        return userService.getUserById(id);
    }

    @PostMapping("/saveUser")
    public void createUser(@RequestBody Users user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}


