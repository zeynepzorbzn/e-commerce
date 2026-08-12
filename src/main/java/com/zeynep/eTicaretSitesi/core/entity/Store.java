package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import com.zeynep.eTicaretSitesi.core.entity.embeddable.StoreAddress;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "stores")
public class Store extends BaseEntity {

    private String name;
    private String email;
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false, unique = true)
    private User owner;
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Product> products;

    @Embedded
    private StoreAddress address;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public User getOwner() {return owner;}
    public void setOwner(User owner) {this.owner = owner;}

    public StoreAddress getAddress() {return address;}
    public void setAddress(StoreAddress address) {this.address = address;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public List<Product> getProducts() {return products;}
    public void setProducts(List<Product> products) {this.products = products;}


}