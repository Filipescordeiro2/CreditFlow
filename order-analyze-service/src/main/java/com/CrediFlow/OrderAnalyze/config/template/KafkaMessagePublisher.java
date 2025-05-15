package com.CrediFlow.OrderAnalyze.config.template;

import com.CrediFlow.OrderAnalyze.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessagePublisher {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void publish(String topic, Order message) {
        kafkaTemplate.send(topic, message);
    }
}
