package com.CrediFlow.OrderAnalyze.config.topics;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.order-created}")
    private String orderCreatedTopicName;

    @Value("${spring.kafka.topic.order-status-updated}")
    private String orderStatusUpdatedTopicName;

    @Bean
    public NewTopic orderCreatedTopic() {
        return new NewTopic(orderCreatedTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderStatusUpdatedTopic() {
        return new NewTopic(orderStatusUpdatedTopicName, 1, (short) 1);
    }
}
