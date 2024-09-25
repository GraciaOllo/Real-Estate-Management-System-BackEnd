package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Agent;
import com.example.demo.model.Tenant;
import com.example.demo.model.Users;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.AgentRepository;
import com.example.demo.repository.TenantRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TenantRepository tenantRepository;
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register Admin User
    public void registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }

    // Register General User (e.g., Agent, Tenant, etc.)
    public void registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    // Authenticate Admin
    public String authenticateAdmin(String email, String password) {
        // Authenticate Admin using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Fetch the authenticated Admin entity
        Admin admin = adminRepository.findByEmail(email);

        // Generate JWT token with the role included
        return jwtUtil.generateToken(new Users(admin.getEmail(), admin.getPassword(), "ADMIN"));
    }


    public String authenticateUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Fetch the authenticated User entity
        Users user = userRepository.findByEmail(email);
        String token = jwtUtil.generateToken(new Users(user.getEmail(), user.getPassword(), "TENANT"));

        return token;
    }

    public void registerTenant(Tenant tenant) {
        tenant.setPassword(passwordEncoder.encode(tenant.getPassword()));
        tenantRepository.save(tenant);
    }
//    public String authenticateTenant(String username, String password) {
//        // Authenticate Tenant using AuthenticationManager
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Fetch the authenticated Tenant entity
//        Optional<Tenant> tenant = tenantRepository.findByUserName(username);
//
//        // Generate JWT token with the role included
//        return jwtUtil.generateToken(new Users (tenant.getUserName(), tenant.getPassword(), "ADMIN"));
//    }

    public void registerAgent(Agent agent) {
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        agentRepository.save(agent);
    }
}
