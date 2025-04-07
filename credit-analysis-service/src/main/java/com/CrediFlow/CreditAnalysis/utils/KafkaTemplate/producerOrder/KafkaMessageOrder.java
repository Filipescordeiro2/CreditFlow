package com.CrediFlow.CreditAnalysis.utils.KafkaTemplate.producerOrder;


import com.CrediFlow.CreditAnalysis.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageOrder implements KafkaMessagePublisherOrder {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void publish(String topic, Order order) {
        kafkaTemplate.send("order-status-updated", order);

    }
}
