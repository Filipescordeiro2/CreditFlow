package com.CrediFlow.OrderAnalyze.mapper;

import com.CrediFlow.OrderAnalyze.domain.Order;
import com.CrediFlow.OrderAnalyze.dto.request.OrderRequest;
import com.CrediFlow.OrderAnalyze.dto.response.OrderResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public static Order toOrder(OrderRequest request){
        return Order
                .builder()
                .cpf(request.getCpf())
                .nameClient(request.getNameClient())
                .handleName(request.getHandleName())
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .isValidCredit(false)
                .build();
    }

    public static OrderResponse toResponse (Order order){
        return OrderResponse
                .builder()
                .message("Avalidacao Enviada com Sucesso")
                .creatAt(order.getCreatAt())
                .build();
    }
}
