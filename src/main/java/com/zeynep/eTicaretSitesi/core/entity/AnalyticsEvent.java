package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import com.zeynep.eTicaretSitesi.core.enums.AnalyticsEventType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(name = "analytics_events")
public class AnalyticsEvent extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AnalyticsEventType eventType;

    @Column
    private Long userId;

    @Column
    private Long storeId;

    @Column
    private Long productId;

    @Column
    private Long categoryId;

    @Column
    private Long brandId;

    @Column
    private Long orderId;

    @Column(length = 100)
    private String sessionId;

    @Column(precision = 19, scale = 2)
    private BigDecimal value;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;

    protected AnalyticsEvent() {
    }

    public AnalyticsEvent(
            AnalyticsEventType eventType,
            Long userId,
            Long storeId,
            Long productId,
            Long categoryId,
            Long brandId,
            Long orderId,
            String sessionId,
            BigDecimal value,
            Map<String, Object> metadata
    ) {
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
    }

    public AnalyticsEventType getEventType() {
        return eventType;
    }

    public void setEventType(AnalyticsEventType eventType) {
        this.eventType = eventType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}