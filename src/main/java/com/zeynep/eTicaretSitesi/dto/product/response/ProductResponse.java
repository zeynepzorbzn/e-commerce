package com.zeynep.eTicaretSitesi.dto.product.response;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.Product;
import com.zeynep.eTicaretSitesi.dto.productImage.ProductImageResponse;
import com.zeynep.eTicaretSitesi.dto.productVariant.ProductVariantResponse;

import java.math.BigDecimal;
import java.util.List;

public class ProductResponse extends BaseResponse<Product> {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String gender;
    private String season;

    private String storeName;
    private String brandName;
    private String categoryName;
    private List<ProductVariantResponse> variants;
    private List<ProductImageResponse> images;

    public ProductResponse() {
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

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

    public String getStoreName() {return storeName;}
    public void setStoreName(String storeName) {this.storeName = storeName;}

    public String getBrandName() {return brandName;}
    public void setBrandName(String brandName) {this.brandName = brandName;}

    public String getCategoryName() {return categoryName;}
    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}

    public List<ProductVariantResponse> getVariants() {return variants;}
    public void setVariants(List<ProductVariantResponse> variants) {this.variants = variants;}

    public List<ProductImageResponse> getImages() {
        return images;
    }

    public void setImages(List<ProductImageResponse> images) {
        this.images = images;
    }
}