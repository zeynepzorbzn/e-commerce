package com.zeynep.eTicaretSitesi.dto.analytics;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StoreAnalyticsResponse {

    private Long storeId;
    private LocalDate date;
    private Long totalViews;
    private Long totalProductsSold;
    private BigDecimal totalRevenue;
    private Long totalOrders;
    private BigDecimal avgOrderValue;
    private BigDecimal returningCustomerRate;
    private BigDecimal conversionRate;

    public StoreAnalyticsResponse() {
    }

    public StoreAnalyticsResponse(
            Long storeId,
            LocalDate date,
            Long totalViews,
            Long totalProductsSold,
            BigDecimal totalRevenue,
            Long totalOrders,
            BigDecimal avgOrderValue,
            BigDecimal returningCustomerRate,
            BigDecimal conversionRate
    ) {
        this.storeId = storeId;
        this.date = date;
        this.totalViews = totalViews;
        this.totalProductsSold = totalProductsSold;
        this.totalRevenue = totalRevenue;
        this.totalOrders = totalOrders;
        this.avgOrderValue = avgOrderValue;
        this.returningCustomerRate = returningCustomerRate;
        this.conversionRate = conversionRate;
    }

    public Long getStoreId() {
        return storeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getTotalViews() {
        return totalViews;
    }

    public Long getTotalProductsSold() {
        return totalProductsSold;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public BigDecimal getAvgOrderValue() {
        return avgOrderValue;
    }

    public BigDecimal getReturningCustomerRate() {
        return returningCustomerRate;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }
}