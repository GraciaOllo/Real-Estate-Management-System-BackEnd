package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.model.Property;
import com.example.demo.model.Tenant;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final PaymentRepository paymentRepository;

    public TenantService(TenantRepository tenantRepository, PaymentRepository paymentRepository) {
        this.tenantRepository = tenantRepository;
        this.paymentRepository = paymentRepository;
    }

    public Tenant createTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Long id, Tenant tenantDetails) {
        Tenant tenant = tenantRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
        tenant.setFirstName(tenantDetails.getFirstName());
        tenant.setLastName(tenantDetails.getLastName());
        tenant.setEmail(tenantDetails.getEmail());
        tenant.setPhoneNumber(tenantDetails.getPhoneNumber());
        return tenantRepository.save(tenant);
    }

    public void deleteTenant(Long id) {
        Tenant tenant = tenantRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
        tenantRepository.delete(tenant);
    }

    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Payment payRent(Long tenantId, BigDecimal amount) {
        Tenant tenant = tenantRepository.findById(Math.toIntExact(tenantId)).orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
        Property property = tenant.getProperty();

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setBooking(null); // Not associated with a booking, just rent payment
        payment.setDescription("Rent payment for property: " + property.getName());

        return paymentRepository.save(payment);
    }
}