package com.CrediFlow.OrderAnalyze.listener;

import com.CrediFlow.OrderAnalyze.domain.Order;
import com.CrediFlow.OrderAnalyze.repository.OrderRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderUpdate{

    private final OrderRespository orderRespository;

    public void updateOrderStatus(Order order) {
        Order existingOrder = orderRespository.findById(order.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ordem não encontrada"));

        existingOrder.setValidCredit(true);
        existingOrder.setUpdateAt(LocalDateTime.now());
        orderRespository.save(existingOrder);
    }
}
