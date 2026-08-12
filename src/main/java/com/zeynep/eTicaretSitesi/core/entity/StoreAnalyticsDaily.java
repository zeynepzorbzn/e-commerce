package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "store_analytics_daily", uniqueConstraints = {@UniqueConstraint(columnNames = {"store_id", "date"})})
public class StoreAnalyticsDaily extends BaseEntity {

    @Column(name = "store_id", nullable = false)
    private Long storeId;
    @Column(nullable = false)
    private LocalDate date;
    private Long totalViews = 0L;
    private Long totalProductsSold = 0L;
    private BigDecimal totalRevenue = BigDecimal.ZERO;
    private Long totalOrders = 0L;
    private BigDecimal avgOrderValue = BigDecimal.ZERO;
    private BigDecimal returningCustomerRate = BigDecimal.ZERO;
    private BigDecimal conversionRate = BigDecimal.ZERO;

    public StoreAnalyticsDaily() {
    }

    public Long getStoreId() {return storeId;}
    public void setStoreId(Long storeId) {this.storeId = storeId;}

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

    public Long getTotalViews() {return totalViews;}
    public void setTotalViews(Long totalViews) {this.totalViews = totalViews;}

    public Long getTotalProductsSold() {return totalProductsSold;}
    public void setTotalProductsSold(Long totalProductsSold) {this.totalProductsSold = totalProductsSold;}

    public BigDecimal getTotalRevenue() {return totalRevenue;}
    public void setTotalRevenue(BigDecimal totalRevenue) {this.totalRevenue = totalRevenue;}

    public Long getTotalOrders() {return totalOrders;}
    public void setTotalOrders(Long totalOrders) {this.totalOrders = totalOrders;}

    public BigDecimal getAvgOrderValue() {return avgOrderValue;}
    public void setAvgOrderValue(BigDecimal avgOrderValue) {this.avgOrderValue = avgOrderValue;}

    public BigDecimal getReturningCustomerRate() {return returningCustomerRate;}
    public void setReturningCustomerRate(BigDecimal returningCustomerRate) {this.returningCustomerRate = returningCustomerRate;}

    public BigDecimal getConversionRate() {return conversionRate;}
    public void setConversionRate(BigDecimal conversionRate) {this.conversionRate = conversionRate;}
}