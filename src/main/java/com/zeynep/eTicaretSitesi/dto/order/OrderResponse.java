package com.zeynep.eTicaretSitesi.dto.order;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.Order;
import com.zeynep.eTicaretSitesi.dto.orderItem.OrderItemResponse;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse extends BaseResponse<Order> {

    private Long id;
    private String code;
    private BigDecimal totalPrice;
    private Integer productCount;
    private Long userId;
    private List<OrderItemResponse> orderItems;
    private Long addressId;
    private Long paymentMethodId;

    public OrderResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
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