package com.urban.exampub.models.DTOs;

public class DrinkResponseDto {
    private Long id;
    private String name;
    private double price;
    private boolean isForAdult;

    public DrinkResponseDto() {
    }

    public DrinkResponseDto(Long id, String name, double price, boolean isForAdult) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isForAdult = isForAdult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }
}
