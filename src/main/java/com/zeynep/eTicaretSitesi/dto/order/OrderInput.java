package com.zeynep.eTicaretSitesi.dto.order;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.Order;

public class OrderInput extends BaseInput<Order> {

    private Long cartId;

    public OrderInput() {
    }

    public Long getCartId() {return cartId;}
    public void setCartId(Long cartId) {this.cartId = cartId;}
}