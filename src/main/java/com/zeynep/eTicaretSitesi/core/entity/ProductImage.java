package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage extends BaseEntity {

    @Column(nullable = false)
    private String imageToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductImage() {
    }

    public String getImageToken() {return imageToken;}
    public void setImageToken(String imageToken) {this.imageToken = imageToken;}

    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
}