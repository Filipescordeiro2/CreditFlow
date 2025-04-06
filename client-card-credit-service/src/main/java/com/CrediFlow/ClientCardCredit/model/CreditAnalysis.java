package com.CrediFlow.ClientCardCredit.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditAnalysis {

    private String id;
    private Order order;
    private Boolean financialIssues;
    private Boolean approvedCredit;
    private Integer scoreClient;
    private BigDecimal limitApproved;
    private LocalDateTime creatAt;
    private LocalDateTime updateAt;
}
