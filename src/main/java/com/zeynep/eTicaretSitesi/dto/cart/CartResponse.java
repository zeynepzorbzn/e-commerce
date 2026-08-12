package com.zeynep.eTicaretSitesi.dto.cart;

import com.zeynep.eTicaretSitesi.core.dao.BaseResponse;
import com.zeynep.eTicaretSitesi.core.entity.Cart;
import com.zeynep.eTicaretSitesi.dto.cartItem.CartItemResponse;

import java.math.BigDecimal;
import java.util.List;

public class CartResponse extends BaseResponse<Cart> {

    private Long id;
    private BigDecimal totalPrice;
    private Integer productCount;
    private Long userId;
    private List<CartItemResponse> items;

    public CartResponse() {
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public BigDecimal getTotalPrice() {return totalPrice;}
    public void setTotalPrice(BigDecimal totalPrice) {this.totalPrice = totalPrice;}

    public Integer getProductCount() {return productCount;}
    public void setProductCount(Integer productCount) {this.productCount = productCount;}

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}

    public List<CartItemResponse> getItems() {return items;}
    public void setItems(List<CartItemResponse> items) {this.items = items;}

}