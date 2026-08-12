package com.zeynep.eTicaretSitesi.dto.cart;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.Cart;

public class CartInput extends BaseInput<Cart> {

    private Long userId;

    public CartInput() {}

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
}