package com.CrediFlow.CreditAnalysis.utils.KafkaTemplate.producerCreditAnalysis;

import com.CrediFlow.CreditAnalysis.domain.CreditAnalysis;

public interface MessagePublisherCreditAnalysis {
    void publish(String topic, CreditAnalysis creditAnalysis);
}
