package com.CrediFlow.CreditAnalysis.utils.KafkaTemplate.producerOrder;

import com.CrediFlow.CreditAnalysis.model.Order;

public interface KafkaMessagePublisherOrder{
    void publish(String topic, Order order);

}
