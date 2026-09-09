package com.zeynep.eTicaretSitesi.service.analytics;

import com.zeynep.eTicaretSitesi.core.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsRabbitPublisher {

    private final RabbitTemplate rabbitTemplate;

    public AnalyticsRabbitPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(AnalyticsEventMessage message) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ANALYTICS_EXCHANGE,
                RabbitMQConfig.ANALYTICS_ROUTING_KEY,
                message
        );
    }
}