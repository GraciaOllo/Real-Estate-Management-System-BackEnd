package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="agent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Agent extends Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agentid;
    private Date workdate;
//    @OneToMany
//    @JoinColumn(name = "Fk_agentid")
//    private Property property;


}
