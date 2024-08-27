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
    public String authenticateAdmin(String username, String password) {
        // Authenticate Admin using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Fetch the authenticated Admin entity
        Admin admin = adminRepository.findByUsername(username);

        // Generate JWT token with the role included
        return jwtUtil.generateToken(new Admin(admin.getUsername(), admin.getPassword(), "ADMIN"));
    }


    public String authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Admin admin = adminRepository.findByUsername(username);

//        Users u = userRepository.findByUsername(username);
        return jwtUtil.generateToken(admin);
//            return u.getUsername();
    }

    public void registerTenant(Tenant tenant) {
        tenant.setPassword(passwordEncoder.encode(tenant.getPassword()));
        tenantRepository.save(tenant);
    }
    public String authenticateTenant(String firstname, String password) {
        // Authenticate Tenant using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(firstname, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Fetch the authenticated Tenant entity
        Tenant tenant = tenantRepository.findByFirstName(firstname);

        // Generate JWT token with the role included
        return jwtUtil.generateToken(new Admin(tenant.getFirstName(), tenant.getPassword(), "ADMIN"));
    }

    public void registerAgent(Agent agent) {
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        agentRepository.save(agent);
    }
}
