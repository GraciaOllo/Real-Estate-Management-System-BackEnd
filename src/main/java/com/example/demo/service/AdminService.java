package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Agent;
import com.example.demo.model.Tenant;
import com.example.demo.model.Users;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.AgentRepository;
import com.example.demo.repository.TenantRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AgentRepository agentRepository;
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, AgentRepository agentRepository, TenantRepository tenantRepository,
                        UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.agentRepository = agentRepository;
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin createAdmin(Admin admin) {
        Users user = new Users();
        user.setUsername(admin.getUsername());
        user.setEmail(admin.getEmail());
        user.setPassword(passwordEncoder.encode(admin.getPassword())); // Default password should be changed later
       admin.setPassword(passwordEncoder.encode((admin.getPassword())));
        user.setRole("ADMIN");
        userRepository.save(user);
        admin.setUserAccount(user);
        return adminRepository.save(admin);
    }

    public Agent createAgent(Agent agent) {
        Users user = new Users();
        user.setUsername(agent.getUserName());
        user.setEmail(agent.getEmail());
        user.setPassword(passwordEncoder.encode(agent.getPassword())); // Default password should be changed later
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        user.setRole("AGENT");
        userRepository.save(user);
        agent.setUserAccount(user);
        return agentRepository.save(agent);
    }

    public Tenant createTenant(Tenant tenant) {
        Users user = new Users();
        user.setUsername(tenant.getUserName());
        user.setEmail(tenant.getEmail());
        user.setPassword(passwordEncoder.encode(tenant.getPassword())); // Default password should be changed later
        tenant.setPassword(passwordEncoder.encode(tenant.getPassword()));
        user.setRole("TENANT");
        userRepository.save(user);
        tenant.setUserAccount(user);
        return tenantRepository.save(tenant);
    }
}