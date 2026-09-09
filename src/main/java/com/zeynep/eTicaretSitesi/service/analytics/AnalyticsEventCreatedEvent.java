package com.zeynep.eTicaretSitesi.service.analytics;

public class AnalyticsEventCreatedEvent {

    private final AnalyticsEventMessage message;

    public AnalyticsEventCreatedEvent(AnalyticsEventMessage message) {
        this.message = message;
    }

    public AnalyticsEventMessage getMessage() {
        return message;
    }
}