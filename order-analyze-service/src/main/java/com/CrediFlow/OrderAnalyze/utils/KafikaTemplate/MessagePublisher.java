package com.CrediFlow.OrderAnalyze.utils.KafikaTemplate;

import com.CrediFlow.OrderAnalyze.domain.Order;

public interface MessagePublisher{
    void publish(String topic, Order order);
}