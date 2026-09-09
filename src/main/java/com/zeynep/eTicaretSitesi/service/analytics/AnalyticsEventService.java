package com.zeynep.eTicaretSitesi.service.analytics;

import com.zeynep.eTicaretSitesi.core.entity.AnalyticsEvent;
import com.zeynep.eTicaretSitesi.core.enums.AnalyticsEventType;
import com.zeynep.eTicaretSitesi.repo.AnalyticsEvent.AnalyticsEventRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AnalyticsEventService {

    private final AnalyticsEventRepository analyticsEventRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AnalyticsEventService(
            AnalyticsEventRepository analyticsEventRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.analyticsEventRepository = analyticsEventRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public AnalyticsEvent track(
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

        AnalyticsEvent event = new AnalyticsEvent(
                eventType,
                userId,
                storeId,
                productId,
                categoryId,
                brandId,
                orderId,
                sessionId,
                value,
                metadata
        );

        AnalyticsEvent savedEvent =
                analyticsEventRepository.save(event);

        AnalyticsEventMessage message =
                new AnalyticsEventMessage(
                        savedEvent.getId(),
                        savedEvent.getEventType(),
                        savedEvent.getUserId(),
                        savedEvent.getStoreId(),
                        savedEvent.getProductId(),
                        savedEvent.getCategoryId(),
                        savedEvent.getBrandId(),
                        savedEvent.getOrderId(),
                        savedEvent.getSessionId(),
                        savedEvent.getValue(),
                        savedEvent.getMetadata(),
                        savedEvent.getCreatedAt()
                );

        eventPublisher.publishEvent(
                new AnalyticsEventCreatedEvent(message)
        );

        return savedEvent;
    }
}