package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="property")

public class Property {

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public enum Statues {
        SOLD,
        OCCUPIED,
        FREE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int property_id;
    private int price;
    private String type,picture,location;
    private Statues statues;
    @JoinColumn(name = "FK_tenant_id")
    @ManyToOne(cascade= CascadeType.ALL)


    Tenant tenant;



}
