package com.zeynep.eTicaretSitesi.dto.productVariant;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.ProductVariant;

public class ProductVariantInput extends BaseInput<ProductVariant> {

    private String size;
    private Integer stock;
    private String color;
    private Long productId;

    public ProductVariantInput(){}

    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}

    public Integer getStock() {return stock;}
    public void setStock(Integer stock) {this.stock = stock;}

    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}

    public Long getProductId() {return productId;}
    public void setProductId(Long productId) {this.productId = productId;}
}
