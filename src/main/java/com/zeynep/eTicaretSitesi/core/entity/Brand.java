package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Product> brandProducts;

    public Brand() {
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public List<Product> getBrandProducts() {return brandProducts;}
    public void setBrandProducts(List<Product> brandProducts) {this.brandProducts = brandProducts;}
}