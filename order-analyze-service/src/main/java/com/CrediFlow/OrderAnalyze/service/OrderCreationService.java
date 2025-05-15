package com.CrediFlow.OrderAnalyze.service;

import com.CrediFlow.OrderAnalyze.config.template.KafkaMessagePublisher;
import com.CrediFlow.OrderAnalyze.dto.request.OrderRequest;
import com.CrediFlow.OrderAnalyze.dto.response.OrderResponse;
import com.CrediFlow.OrderAnalyze.mapper.OrderMapper;
import com.CrediFlow.OrderAnalyze.repository.OrderRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderCreationService {

    private final OrderRespository orderRespository;
    private final KafkaMessagePublisher messagePublisher;

    @Value("${spring.kafka.topic.order-created}")
    private String orderCreatedTopic;

    public OrderResponse creationOrder(OrderRequest request) {
        log.info("Inicializando o servico [OrderCreationService  - creationOrder]");
        var order = OrderMapper.toOrder(request);
        var savedOrder = orderRespository.save(order);

        messagePublisher.publish(orderCreatedTopic, savedOrder);
        log.info("Mensagem publicada no tópico: {}", savedOrder);
        return OrderMapper.toResponse(savedOrder);
    }

}
