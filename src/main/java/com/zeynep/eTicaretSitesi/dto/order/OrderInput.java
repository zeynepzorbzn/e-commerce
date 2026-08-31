package com.zeynep.eTicaretSitesi.dto.order;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.Order;

public class OrderInput extends BaseInput<Order> {

    private Long cartId;
    private Long addressId;
    private Long paymentMethodId;

    public OrderInput() {
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}