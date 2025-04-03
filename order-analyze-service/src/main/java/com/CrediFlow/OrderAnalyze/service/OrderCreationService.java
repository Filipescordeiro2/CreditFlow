package com.CrediFlow.OrderAnalyze.service;

import com.CrediFlow.OrderAnalyze.domain.Order;
import com.CrediFlow.OrderAnalyze.dto.request.OrderRequest;
import com.CrediFlow.OrderAnalyze.dto.response.OrderResponse;
import com.CrediFlow.OrderAnalyze.repository.OrderRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderCreationService {

    private final OrderRespository orderRespository;
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${spring.kafka.topic.order-created}") // Pegando o nome do tópico do application.yml
    private String orderCreatedTopic;

    public OrderResponse creationOrder(OrderRequest request) {
        log.info("Inicializando o servico [OrderCreationService  - creationOrder]");
        var order = toOrder(request);
        var savedOrder = orderRespository.save(order);

        // Enviando a ordem salva para o Kafka
        kafkaTemplate.send(orderCreatedTopic, savedOrder);
        log.info("Enviando para o topic do kafka: {}",savedOrder.toString());
        log.info("Finalizando o servico [OrderCreationService - creationOrder]");
        return toResponse(savedOrder);
    }

    private Order toOrder(OrderRequest request){
        return Order
                .builder()
                .cpf(request.getCpf())
                .nameClient(request.getNameClient())
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .isValidCredit(false)
                .build();
    }

    private OrderResponse toResponse (Order order){
        return OrderResponse
                .builder()
                .message("Avalidacao Enviada com Sucesso")
                .creatAt(order.getCreatAt())
                .build();
    }
}
