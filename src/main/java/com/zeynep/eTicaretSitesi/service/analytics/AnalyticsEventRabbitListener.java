package com.zeynep.eTicaretSitesi.service.analytics;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class AnalyticsEventRabbitListener {

    private final AnalyticsRabbitPublisher rabbitPublisher;

    public AnalyticsEventRabbitListener(
            AnalyticsRabbitPublisher rabbitPublisher
    ) {
        this.rabbitPublisher = rabbitPublisher;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAnalyticsEvent(
            AnalyticsEventCreatedEvent event
    ) {
        rabbitPublisher.publish(event.getMessage());
    }
}