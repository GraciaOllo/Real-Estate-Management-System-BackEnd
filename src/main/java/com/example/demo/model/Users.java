package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Making the email field unique
    private String email;

    private String username;
    private String password;
    private String role; // "ADMIN", "AGENT", "TENANT", "CUSTOMER"

    // Custom methods can be added if needed

    public Users(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


}
