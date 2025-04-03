package com.CrediFlow.OrderAnalyze.controller;

import com.CrediFlow.OrderAnalyze.dto.request.OrderRequest;
import com.CrediFlow.OrderAnalyze.dto.response.OrderResponse;
import com.CrediFlow.OrderAnalyze.service.OrderCreationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderCreationService orderCreationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse creationOrder(@Valid @RequestBody OrderRequest request){
        log.info("Inicializado o endPoint [OrderController - creationOrder] - REQUEST: {}",request);
        var response = orderCreationService.creationOrder(request);
        log.info("Finalizado o endPoint [OrderController - creationOrder] - RESPONSE: {}",response.toString());
        return response;
    }
}
