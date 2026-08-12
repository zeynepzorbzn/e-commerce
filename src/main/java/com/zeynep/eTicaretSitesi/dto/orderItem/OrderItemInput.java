package com.zeynep.eTicaretSitesi.dto.orderItem;

import com.zeynep.eTicaretSitesi.core.dao.BaseInput;
import com.zeynep.eTicaretSitesi.core.entity.OrderItem;

public class OrderItemInput extends BaseInput<OrderItem> {

    private Long productVariantId;
    private Integer quantity;

    public OrderItemInput() {
    }

    public Long getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(Long productVariantId) {
        this.productVariantId = productVariantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}