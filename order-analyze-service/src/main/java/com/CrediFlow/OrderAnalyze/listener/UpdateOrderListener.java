package com.CrediFlow.OrderAnalyze.listener;

import com.CrediFlow.OrderAnalyze.domain.Order;
import com.CrediFlow.OrderAnalyze.utils.KafkaListener.OrderUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateOrderListener {

    private final OrderUpdate orderUpdateService;

    @KafkaListener(topics = "order-status-updated", groupId = "order-group")
    public void updateOrderStatus(Order order) {
        log.info("Inicializado o metodo [UpdateOrderListener - updateOrderStatus]");
        log.info("Recebendo atualização de status para a ordem: {}", order.getId());
        try {
            orderUpdateService.updateOrderStatus(order);
        } catch (IllegalArgumentException e) {
            log.warn("Erro ao atualizar ordem: {}", e.getMessage());
        }
        log.info("Finalizado o metodo [UpdateOrderListener - updateOrderStatus]");
    }
}
