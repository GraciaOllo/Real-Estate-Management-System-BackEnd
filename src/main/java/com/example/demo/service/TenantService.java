package com.example.demo.service;

import com.example.demo.model.Tenant;
import com.example.demo.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    @Autowired
    TenantRepository tenantRepository;
    @Bean
    public List<Tenant> getAllTenants(){
        return tenantRepository.findAll();
    }

    public Tenant getTenantById(Integer TenantId){
        return tenantRepository.findById(TenantId).orElse(null);
    }

    public void saveTenant(Tenant tenant){
        tenantRepository.save(tenant);
    }
    public void deleteTenant(Integer TenantId){
        tenantRepository.deleteById(TenantId);
    }

}
