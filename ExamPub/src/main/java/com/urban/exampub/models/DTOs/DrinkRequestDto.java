package com.urban.exampub.models.DTOs;

public class DrinkRequestDto {
    private String name;
    private Double price;
    private Boolean isForAdult;

    public DrinkRequestDto() {
    }

    public DrinkRequestDto(String name, Double price, Boolean isForAdult) {
        this.name = name;
        this.price = price;
        this.isForAdult = isForAdult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsForAdult() {
        return isForAdult;
    }

    public void setIsForAdult(Boolean forAdult) {
        isForAdult = forAdult;
    }
}
