package com.example.demo.repository;

import com.example.demo.model.Tenant;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository  extends JpaRepository<Tenant, Integer> {
    Optional<Tenant> findByEmail(String email);

}
