package com.CrediFlow.CreditAnalysis.mapper;

import com.CrediFlow.CreditAnalysis.domain.CreditAnalysis;
import com.CrediFlow.CreditAnalysis.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreditAnalysisMapper {


    public static CreditAnalysis toCreditAnalysis(Order order, Integer score,
                                            Boolean approvedCredit, Boolean financialIssue, BigDecimal limite) {
        return CreditAnalysis.builder()
                .order(order)
                .scoreClient(score)
                .approvedCredit(approvedCredit)
                .financialIssues(financialIssue)
                .limitApproved(limite)
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }
}
