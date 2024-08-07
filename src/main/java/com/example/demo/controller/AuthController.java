package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.Users;
import com.example.demo.service.AuthService;
import com.example.demo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        if ("ADMIN".equalsIgnoreCase(user.getRole()) || "AGENT".equalsIgnoreCase(user.getRole()) || "TENANT".equalsIgnoreCase(user.getRole())) {
            return ResponseEntity.status(403).body("Unauthorized role registration!");
        }
        authService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
//        if ("ADMIN".equalsIgnoreCase(user.getRole()) || "AGENT".equalsIgnoreCase(user.getRole()) || "TENANT".equalsIgnoreCase(user.getRole())) {
//            return ResponseEntity.status(403).body("Unauthorized role registration!");
//        }
        authService.registerAdmin(admin);
        return ResponseEntity.ok("Admin registered successfully");
    }

    @PostMapping("/register/agent")
    public ResponseEntity<?> registerAgent(@RequestBody Users agent, @RequestHeader("Authorization") String token) {
        if (!"ADMIN".equals(jwtUtil.extractClaims(token.substring(7)).get("role"))) {
            return ResponseEntity.status(403).body("Only Admin can register an Agent!");
        }
        authService.registerUser(agent);
        return ResponseEntity.ok("Agent registered successfully");
    }

    @PostMapping("/register/tenant")
    public ResponseEntity<?> registerTenant(@RequestBody Users tenant, @RequestHeader("Authorization") String token) {
        if (!"ADMIN".equals(jwtUtil.extractClaims(token.substring(7)).get("role"))) {
            return ResponseEntity.status(403).body("Only Admin can register a Tenant!");
        }
        authService.registerUser(tenant);
        return ResponseEntity.ok("Tenant registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        String jwtToken = authService.authenticateUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(new com.example.demo.security.JwtResponse(jwtToken));
    }
}
