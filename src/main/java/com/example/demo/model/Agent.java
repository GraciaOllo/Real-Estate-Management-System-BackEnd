package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @OneToOne
    private Users userAccount; // Link to User for authentication
}
