package com.example.demo.controller;

import com.example.demo.model.Tenant;
import com.example.demo.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @GetMapping("/getAllTenants")
    public List<Tenant> getAllTenants() {

        return tenantService.getAllTenants();
    }

    @GetMapping("/get-tenants/{id}")
    public Tenant getTenantById(@PathVariable Integer TenantId) {

        return tenantService.getTenantById(TenantId);
    }

    @PostMapping("/saveTenant")
    public void createTenant(@RequestBody Tenant tenant) {
        tenantService.saveTenant(tenant);
    }

    @DeleteMapping("/deleteTenant/{id}")
    public void deleteTenant(@PathVariable Integer TenantId) {
        tenantService.deleteTenant(TenantId);
    }


}
