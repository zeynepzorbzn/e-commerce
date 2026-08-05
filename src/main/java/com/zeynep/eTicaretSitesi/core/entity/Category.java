package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;

    public Category() {}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public List<Product> getProducts() {return products;}
    public void setProducts(List<Product> products) {this.products = products;}
}