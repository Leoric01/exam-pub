package com.urban.exampub.models.DTOs;

import com.urban.exampub.models.Order;

import java.util.List;

public class UserOrderDto {
    private Long id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
    private List<OrderDto> orders;

    public UserOrderDto() {
    }

    public UserOrderDto(Long id, String name, boolean isActive, boolean isAdult, double pocket, List<OrderDto> orders) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.isAdult = isAdult;
        this.pocket = pocket;
        this.orders = orders;
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
        return pocket;
    }

    public void setPocket(double pocket) {
        this.pocket = pocket;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }
}
