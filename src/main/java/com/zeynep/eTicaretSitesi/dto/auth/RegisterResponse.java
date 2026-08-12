package com.zeynep.eTicaretSitesi.dto.auth;

import com.zeynep.eTicaretSitesi.core.enums.RoleName;

public class RegisterResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private RoleName roleName;
    private String accessToken;
    private String refreshToken;

    public RegisterResponse() {
    }
    public RegisterResponse(Long id, String firstName, String lastName, String email, String phoneNumber, RoleName roleName,
                            String accessToken, String refreshToken) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roleName = roleName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public RoleName getRoleName() {return roleName;}
    public void setRoleName(RoleName roleName) {this.roleName = roleName;}

    public String getAccessToken() {return accessToken;}
    public void setAccessToken(String accessToken) {this.accessToken = accessToken;}

    public String getRefreshToken() {return refreshToken;}
    public void setRefreshToken(String refreshToken) {

        this.refreshToken = refreshToken;
    }
}