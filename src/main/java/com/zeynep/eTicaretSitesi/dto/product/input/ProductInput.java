package com.zeynep.eTicaretSitesi.dto.product.input;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.Product;

import java.math.BigDecimal;

public class ProductInput extends BaseInput<Product> {

    private String name;
    private String description;
    private BigDecimal price;
    private String gender;
    private String season;
    //private Integer stock;

    private Long storeId;
    private Long brandId;
    private Long categoryId;

    public ProductInput() {
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

    public Long getStoreId() {return storeId;}
    public void setStoreId(Long storeId) {this.storeId = storeId;}

    public Long getBrandId() {return brandId;}
    public void setBrandId(Long brandId) {this.brandId = brandId;}

    public Long getCategoryId() {return categoryId;}
    public void setCategoryId(Long categoryId) {this.categoryId = categoryId;}

//    public Integer getStock() {
//        return stock;
//    }
//
//    public void setStock(Integer stock) {
//        this.stock = stock;
//    }
}