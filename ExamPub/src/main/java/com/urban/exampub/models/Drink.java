package com.urban.exampub.models;

import jakarta.persistence.*;

@Entity
public class Drink extends Product{

    private boolean isForAdult;

    public Drink() {
    }

    public Drink(String productName, double price, boolean isForAdult) {
        super(productName, price);
        this.isForAdult = isForAdult;
    }
    @Id
    public Long getId() {
        return super.getId();
    }
    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }

}
