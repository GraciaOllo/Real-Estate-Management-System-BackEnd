package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Bean
    public List<Admin> getAllAdmins() {return adminRepository.findAll();
    }

    public Admin getAdminById(Integer AdminId) {
        return adminRepository.findById(AdminId).orElse(null);
    }

    public Admin saveAdmin(Admin admin) {
        adminRepository.save(admin);
        return admin;
    }

    public void deleteAdmin(Integer AdminId) {
        adminRepository.deleteById(AdminId);
    }


}
