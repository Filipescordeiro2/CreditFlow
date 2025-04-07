package com.CrediFlow.CreditAnalysis.utils.KafkaTemplate.producerCreditAnalysis;

import com.CrediFlow.CreditAnalysis.domain.CreditAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessagePublisherCreditAnalysis implements MessagePublisherCreditAnalysis {

    private final KafkaTemplate<String, CreditAnalysis> kafkaTemplate;

    @Override
    public void publish(String topic, CreditAnalysis message) {
        kafkaTemplate.send(topic, message);
    }
}

