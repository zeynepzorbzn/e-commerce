package com.zeynep.eTicaretSitesi.dto.productVariant;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;

public class ProductVariantResponse extends BaseResponse<ProductVariant> {

    private Long id;
    private String size;
    private Integer stock;
    private String color;
    private Long productId;
    private String productName;

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


}
