package com.urban.exampub.models;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private int amount;
    private double price;
    @ManyToOne
    private User user;

    public Order() {
    }

    public Order(String productName, int amount, double price, User user) {
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
