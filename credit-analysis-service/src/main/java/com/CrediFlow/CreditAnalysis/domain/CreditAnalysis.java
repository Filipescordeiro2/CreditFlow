package com.CrediFlow.CreditAnalysis.domain;

import com.CrediFlow.CreditAnalysis.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "creditAnalysis")
public class CreditAnalysis {

    @Id
    private String id;

    @DBRef
    private Order order;

    private Boolean financialIssues;
    private Boolean approvedCredit;

    private Integer scoreClient;
    private BigDecimal limitApproved;

    private LocalDateTime creatAt;
    private LocalDateTime updateAt;

}
