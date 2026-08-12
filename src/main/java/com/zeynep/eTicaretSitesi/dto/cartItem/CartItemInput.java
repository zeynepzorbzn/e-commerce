package com.zeynep.eTicaretSitesi.dto.cartItem;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.CartItem;

public class CartItemInput extends BaseInput<CartItem> {

    private Long productVariantId;
    private Integer quantity;

    public CartItemInput() {
    }

    public Long getProductVariantId() {return productVariantId;}
    public void setProductVariantId(Long productVariantId) {this.productVariantId = productVariantId;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}
}