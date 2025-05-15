package com.CrediFlow.CreditAnalysis.config.template;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessagePublisher{

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(String topic, Object object) {
        kafkaTemplate.send(topic, object);
    }
}
