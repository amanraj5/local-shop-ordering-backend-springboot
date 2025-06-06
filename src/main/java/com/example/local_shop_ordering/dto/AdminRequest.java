package com.example.local_shop_ordering.dto;

public class AdminRequest {
    private String email;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminRequest(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AdminRequest() {
    }
}
