package com.urban.exampub.models;

import jakarta.persistence.*;

@Entity
public class Drink extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isForAdult;

    public Drink() {
    }

    public Drink(String productName, double price, boolean isForAdult) {
        super(productName, price);
        this.isForAdult = isForAdult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }
}
