package com.urban.exampub.models;

public class UserLoginRequest {
    private String name;
    private String password;

    public UserLoginRequest() {
    }

    public UserLoginRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
