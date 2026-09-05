package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_variants")
public class ProductVariant extends BaseEntity {

    private String size;
    private Integer stock;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY)
    private List<ProductImage> images = new ArrayList<>();

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

    public List<OrderItem> getOrderItems() {return orderItems;}
    public void setOrderItems(List<OrderItem> orderItems) {this.orderItems = orderItems;}

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }
}