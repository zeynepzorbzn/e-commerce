package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    private String name;
    private String city;
    private String district;
    private String street;
    private String postalCode;
    private boolean billing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Address() {
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getDistrict() {return district;}
    public void setDistrict(String district) {this.district = district;}

    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}

    public String getPostalCode() {return postalCode;}
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    public boolean getBilling() {return billing;}
    public void setBilling(boolean billing) {this.billing = billing;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}