package com.zeynep.eTicaretSitesi.dto.analytics;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlatformAnalyticsResponse {

    private LocalDate date;
    private Long totalUsers;
    private Long activeUsers;
    private Long newUsers;
    private BigDecimal totalRevenue;
    private Long totalOrders;
    private Long totalStores;
    private Long topSellingProductId;
    private Long topStoreId;
    private BigDecimal systemConversionRate;

    public PlatformAnalyticsResponse() {
    }

    public PlatformAnalyticsResponse(
            LocalDate date,
            Long totalUsers,
            Long activeUsers,
            Long newUsers,
            BigDecimal totalRevenue,
            Long totalOrders,
            Long totalStores,
            Long topSellingProductId,
            Long topStoreId,
            BigDecimal systemConversionRate
    ) {
        this.date = date;
        this.totalUsers = totalUsers;
        this.activeUsers = activeUsers;
        this.newUsers = newUsers;
        this.totalRevenue = totalRevenue;
        this.totalOrders = totalOrders;
        this.totalStores = totalStores;
        this.topSellingProductId = topSellingProductId;
        this.topStoreId = topStoreId;
        this.systemConversionRate = systemConversionRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public Long getActiveUsers() {
        return activeUsers;
    }

    public Long getNewUsers() {
        return newUsers;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public Long getTotalStores() {
        return totalStores;
    }

    public Long getTopSellingProductId() {
        return topSellingProductId;
    }

    public Long getTopStoreId() {
        return topStoreId;
    }

    public BigDecimal getSystemConversionRate() {
        return systemConversionRate;
    }
}