package com.zeynep.eTicaretSitesi.service.analytics;

import com.zeynep.eTicaretSitesi.core.enums.AnalyticsEventType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class AnalyticsEventMessage {

    private Long id;
    private AnalyticsEventType eventType;
    private Long userId;
    private Long storeId;
    private Long productId;
    private Long categoryId;
    private Long brandId;
    private Long orderId;
    private String sessionId;
    private BigDecimal value;
    private Map<String, Object> metadata;
    private LocalDateTime createdAt;

    public AnalyticsEventMessage() {
    }

    public AnalyticsEventMessage(
            Long id,
            AnalyticsEventType eventType,
            Long userId,
            Long storeId,
            Long productId,
            Long categoryId,
            Long brandId,
            Long orderId,
            String sessionId,
            BigDecimal value,
            Map<String, Object> metadata,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.eventType = eventType;
        this.userId = userId;
        this.storeId = storeId;
        this.productId = productId;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.orderId = orderId;
        this.sessionId = sessionId;
        this.value = value;
        this.metadata = metadata;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public AnalyticsEventType getEventType() {
        return eventType;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}