package com.zeynep.eTicaretSitesi.dto.store;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.Store;

public class StoreResponse extends BaseResponse<Store> {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String ownerName;
    private String city;
    private String district;
    private String street;
    private String postalCode;


    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getOwnerName() {return ownerName;}
    public void setOwnerName(String ownerName) {this.ownerName = ownerName;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getDistrict() {return district;}
    public void setDistrict(String district) {this.district = district;}

    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}

    public String getPostalCode() {return postalCode;}
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}
}
