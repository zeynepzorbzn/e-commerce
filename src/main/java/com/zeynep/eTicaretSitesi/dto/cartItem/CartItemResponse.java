package com.zeynep.eTicaretSitesi.dto.cartItem;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.CartItem;

import java.math.BigDecimal;

public class CartItemResponse extends BaseResponse<CartItem> {

    private Long id;
    private Integer quantity;
    private BigDecimal totalPrice;

    private Long productVariantId;
    private String productName;
    private String size;
    private String color;

    public CartItemResponse() {
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

    public BigDecimal getTotalPrice() {return totalPrice;}
    public void setTotalPrice(BigDecimal totalPrice) {this.totalPrice = totalPrice;}

    public Long getProductVariantId() {return productVariantId;}
    public void setProductVariantId(Long productVariantId) {this.productVariantId = productVariantId;}

    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}

    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}

    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}
}