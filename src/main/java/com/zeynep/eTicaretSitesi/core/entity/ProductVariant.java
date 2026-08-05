package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "product_variants")
public class ProductVariant extends BaseEntity {

    private String size;
    private Integer stock;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductVariant() {
    }

    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}

    public Integer getStock() {return stock;}
    public void setStock(Integer stock) {this.stock = stock;}

    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}

    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
}