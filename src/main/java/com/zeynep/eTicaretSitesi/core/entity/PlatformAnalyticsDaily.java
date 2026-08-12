package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "platform_analytics_daily", uniqueConstraints = {@UniqueConstraint(columnNames = {"date"})})
public class PlatformAnalyticsDaily extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;
    private Long totalUsers = 0L;
    private Long activeUsers = 0L;
    private Long newUsers = 0L;
    private BigDecimal totalRevenue = BigDecimal.ZERO;
    private Long totalOrders = 0L;
    private Long totalStores = 0L;
    private Long topSellingProductId;
    private Long topStoreId;
    private BigDecimal systemConversionRate = BigDecimal.ZERO;

    public PlatformAnalyticsDaily() {
    }

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

    public Long getTotalUsers() {return totalUsers;}
    public void setTotalUsers(Long totalUsers) {this.totalUsers = totalUsers;}

    public Long getActiveUsers() {return activeUsers;}
    public void setActiveUsers(Long activeUsers) {this.activeUsers = activeUsers;}

    public Long getNewUsers() {return newUsers;}
    public void setNewUsers(Long newUsers) {this.newUsers = newUsers;}

    public BigDecimal getTotalRevenue() {return totalRevenue;}
    public void setTotalRevenue(BigDecimal totalRevenue) {this.totalRevenue = totalRevenue;}

    public Long getTotalOrders() {return totalOrders;}
    public void setTotalOrders(Long totalOrders) {this.totalOrders = totalOrders;}

    public Long getTotalStores() {return totalStores;}
    public void setTotalStores(Long totalStores) {this.totalStores = totalStores;}

    public Long getTopSellingProductId() {return topSellingProductId;}
    public void setTopSellingProductId(Long topSellingProductId) {this.topSellingProductId = topSellingProductId;}

    public Long getTopStoreId() {return topStoreId;}
    public void setTopStoreId(Long topStoreId) {this.topStoreId = topStoreId;}

    public BigDecimal getSystemConversionRate() {return systemConversionRate;}
    public void setSystemConversionRate(BigDecimal systemConversionRate) {this.systemConversionRate = systemConversionRate;}
}