package com.CrediFlow.CreditAnalysis.repository;

import com.CrediFlow.CreditAnalysis.domain.CreditAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditAnalysisRepository extends MongoRepository<CreditAnalysis,String> {
}
