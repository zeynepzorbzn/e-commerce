package com.zeynep.eTicaretSitesi.dto.auth;


public class LoginInput {

    private String email;
    private String password;

    public LoginInput() {
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


}