package com.CrediFlow.OrderAnalyze.utils.KafkaListener;

import com.CrediFlow.OrderAnalyze.domain.Order;

public interface OrderUpdate {
    void updateOrderStatus(Order order);

}
