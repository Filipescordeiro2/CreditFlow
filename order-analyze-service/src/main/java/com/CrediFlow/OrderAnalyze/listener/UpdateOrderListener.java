package com.CrediFlow.OrderAnalyze.listener;

import com.CrediFlow.OrderAnalyze.domain.Order;
import com.CrediFlow.OrderAnalyze.repository.OrderRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateOrderListener {

    private final OrderRespository orderRespository;

    @KafkaListener(topics = "order-status-updated", groupId = "order-group")
    public void updateOrderStatus(Order order) {
        log.info("Inicializado o metodo [UpdateOrderListener - updateOrderStatus]");
        log.info("Recebendo atualização de status para a ordem: {}", order.getId());
        Order existingOrder = orderRespository.findById(order.getId())
                .orElseThrow(() -> {
                    log.warn("Ordem {} não encontrada!", order.getId());
                    return new IllegalArgumentException("Ordem não encontrada");
                });
        updateOrder(existingOrder);
        log.info("Finalizado o metodo [UpdateOrderListener - updateOrderStatus]");
    }

    private void updateOrder(Order order) {
        order.setValidCredit(true);
        order.setUpdateAt(LocalDateTime.now());
        orderRespository.save(order);
        log.info("Ordem {} atualizada com sucesso {}", order.getId(),order);
    }
}
