package com.zeynep.eTicaretSitesi.dto.productImage;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.ProductImage;

public class ProductImageInput extends BaseInput<ProductImage> {

    private Long productId;

    public ProductImageInput() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}