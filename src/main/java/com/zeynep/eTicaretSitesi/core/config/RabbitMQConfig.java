package com.zeynep.eTicaretSitesi.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.ApplicationRunner;

@Configuration
public class RabbitMQConfig {

    public static final String ANALYTICS_EXCHANGE = "analytics.exchange";
    public static final String ANALYTICS_QUEUE = "analytics.queue";
    public static final String ANALYTICS_ROUTING_KEY = "analytics.event";




    @Bean
    public DirectExchange analyticsExchange() {
        return new DirectExchange(
                ANALYTICS_EXCHANGE,
                true,
                false
        );
    }

    @Bean
    public Queue analyticsQueue() {
        return new Queue(
                ANALYTICS_QUEUE,
                true
        );
    }

    @Bean
    public Binding analyticsBinding(
            Queue analyticsQueue,
            DirectExchange analyticsExchange
    ) {
        return BindingBuilder
                .bind(analyticsQueue)
                .to(analyticsExchange)
                .with(ANALYTICS_ROUTING_KEY);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        System.out.println(">>> RABBIT ADMIN CREATED");
//        System.out.println(">>> CONNECTION FACTORY: " + connectionFactory);

        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);

        return rabbitAdmin;
    }
    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

//    @Bean
//    public ApplicationRunner rabbitMqTopologyInitializer(RabbitAdmin rabbitAdmin) {
//        return args -> {
//            System.out.println(">>> RABBIT INITIALIZE START");
//
//            rabbitAdmin.initialize();
//
//            System.out.println(">>> RABBIT INITIALIZE SUCCESS");
//        };
//    }
}