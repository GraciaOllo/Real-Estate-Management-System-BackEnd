package com.example.demo.service;

import com.example.demo.model.Tenant;
import com.example.demo.model.Users;
import com.example.demo.repository.TenantRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private TenantRepository tenantRepository;

    public Optional<Tenant> login(String email, String password) {
        Optional<Tenant> tenant = tenantRepository.findByEmail(email);
        if (tenant.isPresent() && tenant.get().login(email, password)) {
            return tenant;
        }
        return Optional.empty();
    }

    public void logout(Users user) {
        user.logout();
    }

    public boolean verifyLogin(Users user) {
        return user.verifyLogin();
    }

}
