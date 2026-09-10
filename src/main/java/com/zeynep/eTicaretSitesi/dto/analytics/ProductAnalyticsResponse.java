package com.zeynep.eTicaretSitesi.dto.analytics;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductAnalyticsResponse {

    private Long productId;
    private Long storeId;
    private LocalDate date;
    private Long viewCount;
    private Long cartAddCount;
    private Long purchaseCount;
    private BigDecimal revenue;
    private BigDecimal conversionRate;
    private BigDecimal avgPrice;
    private BigDecimal stockImpactScore;

    public ProductAnalyticsResponse() {
    }

    public ProductAnalyticsResponse(
            Long productId,
            Long storeId,
            LocalDate date,
            Long viewCount,
            Long cartAddCount,
            Long purchaseCount,
            BigDecimal revenue,
            BigDecimal conversionRate,
            BigDecimal avgPrice,
            BigDecimal stockImpactScore
    ) {
        this.productId = productId;
        this.storeId = storeId;
        this.date = date;
        this.viewCount = viewCount;
        this.cartAddCount = cartAddCount;
        this.purchaseCount = purchaseCount;
        this.revenue = revenue;
        this.conversionRate = conversionRate;
        this.avgPrice = avgPrice;
        this.stockImpactScore = stockImpactScore;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public Long getCartAddCount() {
        return cartAddCount;
    }

    public Long getPurchaseCount() {
        return purchaseCount;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public BigDecimal getStockImpactScore() {
        return stockImpactScore;
    }
}