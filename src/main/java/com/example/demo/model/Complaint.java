package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tenant tenant;

    @ManyToOne
    private Property property;

    private String description;
    private LocalDateTime dateCreated;

    public enum ComplaintStatus {
        PENDING,
        RESOLVED,
        REJECTED
    }

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status = ComplaintStatus.PENDING;
}