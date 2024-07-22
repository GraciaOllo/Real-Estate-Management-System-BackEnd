package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tenantId;
    private boolean payRent;

    public boolean isPayRent() {
        return payRent;
    }

    public void setPayRent(boolean payRent) {
        this.payRent = payRent;
    }

    public Tenant() {
    }
    public Tenant(int tenantId, boolean payRent) {
        this.tenantId=tenantId;
        this.payRent=payRent;
    }
    public Tenant(boolean payRent) {
        this.tenantId=tenantId;
        this.payRent=payRent;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }
}
