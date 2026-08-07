package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String gender;
    private String season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductVariant> productVariants;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductImage> productImages;

    public Product() {
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public BigDecimal getPrice() {return price;}
    public void setPrice(BigDecimal price) {this.price = price;}

    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}

    public String getSeason() {return season;}
    public void setSeason(String season) {this.season = season;}

    public Store getStore() {return store;}
    public void setStore(Store store) {this.store = store;}

    public Brand getBrand() {return brand;}
    public void setBrand(Brand brand) {this.brand = brand;}

    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}

    public List<ProductVariant> getProductVariants() {return productVariants;}
    public void setProductVariants(List<ProductVariant> productVariants) {this.productVariants = productVariants;}

    public List<ProductImage> getProductImages() {return productImages;}
    public void setProductImages(List<ProductImage> productImages) {this.productImages = productImages;}
}