package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    private BigDecimal totalPrice = BigDecimal.ZERO;
    private Integer productCount = 0;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Cart() {
    }

    public BigDecimal getTotalPrice() {return totalPrice;}
    public void setTotalPrice(BigDecimal totalPrice) {this.totalPrice = totalPrice;}

    public Integer getProductCount() {return productCount;}
    public void setProductCount(Integer productCount) {this.productCount = productCount;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}