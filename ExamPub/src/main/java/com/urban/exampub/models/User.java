package com.urban.exampub.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double Pocket;
    @OneToMany
    private List<Order> orders;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
