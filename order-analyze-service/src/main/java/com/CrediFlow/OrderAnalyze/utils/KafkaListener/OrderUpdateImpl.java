package com.CrediFlow.OrderAnalyze.utils.KafkaListener;

import com.CrediFlow.OrderAnalyze.domain.Order;
import com.CrediFlow.OrderAnalyze.repository.OrderRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderUpdateImpl implements OrderUpdate{


    private final OrderRespository orderRespository;

    @Override
    public void updateOrderStatus(Order order) {
        Order existingOrder = orderRespository.findById(order.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ordem não encontrada"));

        existingOrder.setValidCredit(true);
        existingOrder.setUpdateAt(LocalDateTime.now());
        orderRespository.save(existingOrder);
    }
}
