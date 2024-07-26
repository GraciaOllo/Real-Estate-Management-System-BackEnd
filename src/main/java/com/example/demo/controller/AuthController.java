package com.example.demo.controller;

import com.example.demo.model.Tenant;
import com.example.demo.model.Users;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        Optional<Tenant> user = authService.login(email, password);
        if (user.isPresent()) {
            return "Login successful";
        }
        return "Invalid credentials";
    }

    @PostMapping("/logout")
    public String logout(@RequestBody Users user) {
        authService.logout(user);
        return "Logout successful";
    }

    @GetMapping("/verify-login")
    public boolean verifyLogin(@RequestBody Users user) {
        return authService.verifyLogin(user);
    }
}
