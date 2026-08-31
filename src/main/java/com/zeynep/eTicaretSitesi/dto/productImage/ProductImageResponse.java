package com.zeynep.eTicaretSitesi.dto.productImage;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.ProductImage;

public class ProductImageResponse extends BaseResponse<ProductImage> {

    private Long id;
    private String imageToken;
    private Long productId;

    public ProductImageResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageToken() {
        return imageToken;
    }

    public void setImageToken(String imageToken) {
        this.imageToken = imageToken;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}