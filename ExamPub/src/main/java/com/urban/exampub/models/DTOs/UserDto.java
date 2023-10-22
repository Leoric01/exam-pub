package com.urban.exampub.models.DTOs;

public class UserDto {
    private Long id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;

    public UserDto() {
    }

    public UserDto(Long id, String name, boolean isAdult, double pocket) {
        this.id = id;
        this.name = name;
        this.isAdult = isAdult;
        this.pocket = pocket;
    }

    public UserDto(Long id, String name, boolean isActive, boolean isAdult, double pocket) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.isAdult = isAdult;
        this.pocket = pocket;
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
}
