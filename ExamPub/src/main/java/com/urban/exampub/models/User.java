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

    public User() {
    }

    public User(String name, boolean isActive, boolean isAdult, double pocket, List<Order> orders) {
        this.name = name;
        this.isActive = isActive;
        this.isAdult = isAdult;
        Pocket = pocket;
        this.orders = orders;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public double getPocket() {
        return Pocket;
    }

    public void setPocket(double pocket) {
        Pocket = pocket;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
