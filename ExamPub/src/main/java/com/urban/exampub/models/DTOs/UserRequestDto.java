package com.urban.exampub.models.DTOs;

public class UserRequestDto {
    private String name;
    private Boolean isAdult;
    private Double pocket;

    public UserRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        this.isAdult = isAdult;
    }

    public Double getPocket() {
        return pocket;
    }

    public void setPocket(Double pocket) {
        this.pocket = pocket;
    }
}

