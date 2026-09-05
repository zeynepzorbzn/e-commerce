package com.zeynep.eTicaretSitesi.dto.productVariant;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;
import com.zeynep.eTicaretSitesi.dto.productImage.ProductImageResponse;

import java.util.List;

public class ProductVariantResponse extends BaseResponse<ProductVariant> {

    private Long id;
    private String size;
    private Integer stock;
    private String color;
    private Long productId;
    private String productName;
    private List<ProductImageResponse> images;

    public ProductVariantResponse() {}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}

    public Integer getStock() {return stock;}
    public void setStock(Integer stock) {this.stock = stock;}

    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}

    public Long getProductId() {return productId;}
    public void setProductId(Long productId) {this.productId = productId;}

    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}


    public List<ProductImageResponse> getImages() {
        return images;
    }

    public void setImages(List<ProductImageResponse> images) {
        this.images = images;
    }
}
