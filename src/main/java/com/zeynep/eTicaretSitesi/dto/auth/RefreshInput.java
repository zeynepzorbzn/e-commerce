package com.zeynep.eTicaretSitesi.dto.auth;

public class RefreshInput {

    private String refreshToken;

    public RefreshInput(){
    }
    public String getRefreshToken() {return refreshToken;}
    public void setRefreshToken(String refreshToken) {this.refreshToken = refreshToken;}
}
