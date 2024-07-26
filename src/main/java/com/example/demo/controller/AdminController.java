package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/getAllAdmins")
    public List<Admin> getAllAdmins(){
        return adminService.getAllAdmins();
    }
    @GetMapping("/get-admin/{AdminId}")
    public Admin getAdminById(@PathVariable Integer AdminId) {

        return adminService.getAdminById(AdminId);
    }

    @PostMapping("/saveAdmin")
    public void createAdmin(@RequestBody Admin admin) {
        adminService.saveAdmin(admin);
    }


    @DeleteMapping("/deleteAdmin/{AdminId}")
    public void deleteAdmin(@PathVariable Integer AdminId) {
        adminService.deleteAdmin(AdminId);
    }

}
