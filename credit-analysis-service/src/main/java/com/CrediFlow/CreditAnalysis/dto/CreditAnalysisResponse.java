package com.CrediFlow.CreditAnalysis.dto;

import com.CrediFlow.CreditAnalysis.model.Order;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record CreditAnalysisResponse(Order order,
                                     Integer scoreClient,
                                     Boolean approvedCredit,
                                     Boolean financialIssues,
                                     BigDecimal limitApproved,
                                     LocalDateTime creatAt,
                                     LocalDateTime updateAt) {
    }

