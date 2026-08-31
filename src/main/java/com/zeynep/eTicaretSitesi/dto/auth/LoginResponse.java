package com.zeynep.eTicaretSitesi.dto.auth;

import com.zeynep.eTicaretSitesi.core.enums.RoleName;

public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private RoleName roleName;

    public LoginResponse() {
    }
    public LoginResponse(String accessToken, String refreshToken, RoleName roleName
    ) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.roleName = roleName;
    }

    public String getAccessToken() {return accessToken;}
    public void setAccessToken(String accessToken) {this.accessToken = accessToken;}

    public String getRefreshToken() {return refreshToken;}
    public void setRefreshToken(String refreshToken) {this.refreshToken = refreshToken;}

    public RoleName getRoleName() {return roleName;}
    public void setRoleName(RoleName roleName) {this.roleName = roleName;}
}