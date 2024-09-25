package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String location;
    private BigDecimal price;
    private String type;

    private String imageUrl; // URL of the property image

    @ManyToOne
    private Agent agent;

    @OneToOne
    private Customer owner;


    public void setStatus(PropertyStatus propertyStatus) {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public PropertyStatus getStatus() {
        return status;
    }


    public enum PropertyStatus {
        FREE,
        SOLD,
        RENTED
    }
    @Enumerated(EnumType.STRING)
    private PropertyStatus status = PropertyStatus.FREE; // Default status is FREE

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Complaint> complaints;
}
