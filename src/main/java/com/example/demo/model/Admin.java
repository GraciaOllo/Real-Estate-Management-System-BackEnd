package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="admin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Users  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminid;
private String address;



}
