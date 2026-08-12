package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product_analytics_daily", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "date"})})
public class ProductAnalyticsDaily extends BaseEntity {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @Column(nullable = false)
    private LocalDate date;
    private Long viewCount = 0L;
    private Long cartAddCount = 0L;
    private Long purchaseCount = 0L;
    private BigDecimal revenue = BigDecimal.ZERO;
    private BigDecimal conversionRate = BigDecimal.ZERO;
    private BigDecimal avgPrice = BigDecimal.ZERO;
    private BigDecimal stockImpactScore = BigDecimal.ZERO;

    public ProductAnalyticsDaily() {
    }

    public Long getProductId() {return productId;}
    public void setProductId(Long productId) {this.productId = productId;}

    public Long getStoreId() {return storeId;}
    public void setStoreId(Long storeId) {this.storeId = storeId;}

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

    public Long getViewCount() {return viewCount;}
    public void setViewCount(Long viewCount) {this.viewCount = viewCount;}

    public Long getCartAddCount() {return cartAddCount;}
    public void setCartAddCount(Long cartAddCount) {this.cartAddCount = cartAddCount;}

    public Long getPurchaseCount() {return purchaseCount;}
    public void setPurchaseCount(Long purchaseCount) {this.purchaseCount = purchaseCount;}

    public BigDecimal getRevenue() {return revenue;}
    public void setRevenue(BigDecimal revenue) {this.revenue = revenue;}

    public BigDecimal getConversionRate() {return conversionRate;}
    public void setConversionRate(BigDecimal conversionRate) {this.conversionRate = conversionRate;}

    public BigDecimal getAvgPrice() {return avgPrice;}
    public void setAvgPrice(BigDecimal avgPrice) {this.avgPrice = avgPrice;}

    public BigDecimal getStockImpactScore() {return stockImpactScore;}
    public void setStockImpactScore(BigDecimal stockImpactScore) {this.stockImpactScore = stockImpactScore;}
}