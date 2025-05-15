package com.CrediFlow.CreditAnalysis.listener;


import com.CrediFlow.CreditAnalysis.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final CreditAnalysis creditAnalysis;

    @KafkaListener(topics = "order-created", groupId = "credit-analysis-group")
    public void consumeOrder(Order order) {
        log.info("Inicializado o metodo [OrderListener - consumeOrder]");
        log.info("Pedido recebido para análise de crédito: {}", order);

        order.setValidCredit(true);
        order.setUpdateAt(LocalDateTime.now());

        creditAnalysis.analyze(order);

        log.info("Finalizando o metodo [OrderListener - consumeOrder]");
    }
}
