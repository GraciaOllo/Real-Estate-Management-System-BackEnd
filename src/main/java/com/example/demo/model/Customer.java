package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String UserName;
    private String email;
    private String phoneNumber;

    @OneToOne
    private Users userAccount;
}
