package com.example.demo.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password; // Make sure to hash this

    @Transient
    private boolean loggedIn;

    // Getters and Setters

    public boolean login(String email, String password) {
        if (Objects.equals(this.email, email) && Objects.equals(this.password, password)) {
            this.loggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() {
        this.loggedIn = false;
    }

    public boolean verifyLogin() {
        return this.loggedIn;
    }
}