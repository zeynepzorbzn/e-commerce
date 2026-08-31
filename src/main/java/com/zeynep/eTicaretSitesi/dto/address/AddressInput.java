package com.zeynep.eTicaretSitesi.dto.address;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.Address;

public class AddressInput extends BaseInput<Address> {

    private String name;
    private String city;
    private String district;
    private String street;
    private String postalCode;
    private boolean billing;

    public AddressInput() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isBilling() {
        return billing;
    }

    public void setBilling(boolean billing) {
        this.billing = billing;
    }
}