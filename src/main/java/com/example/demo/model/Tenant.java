package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
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
    private String password;

    @ManyToOne
    private Property property;

    @OneToOne
    private Users userAccount; // Link to User for authentication


    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<Complaint> complaints;

    public Tenant(String firstName , String password) {
        this.firstName = firstName;
        this.password = password;
    }

    public boolean login(String email, String password) {
        return false;
    }



}