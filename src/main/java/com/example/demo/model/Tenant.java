package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @ManyToOne
    private Property property;

    @OneToOne
    private Users userAccount; // Link to User for authentication


    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<Complaint> complaints;

    public boolean login(String email, String password) {
        return false;
    }
}
