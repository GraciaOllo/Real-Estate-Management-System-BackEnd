package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.STRING)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private LocalDateTime paymentDate;

    @ManyToOne
    private Booking booking;

    public void setDescription(String s) {
    }

    // Common attributes and methods for all payment types
}
