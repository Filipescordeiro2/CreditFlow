package com.CrediFlow.OrderAnalyze.repository;

import com.CrediFlow.OrderAnalyze.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRespository extends MongoRepository<Order, String> {
}
