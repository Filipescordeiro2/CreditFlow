package com.CrediFlow.CreditAnalysis.service;

import com.CrediFlow.CreditAnalysis.repository.CreditAnalysisRepository;
import com.CrediFlow.CreditAnalysis.domain.CreditAnalysis;
import com.CrediFlow.CreditAnalysis.model.Order;
import com.CrediFlow.CreditAnalysis.utils.CreditAnalysis.LimiteClient;
import com.CrediFlow.CreditAnalysis.utils.CreditAnalysis.ScoreClient;
import com.CrediFlow.CreditAnalysis.validation.CreditAnalysis.ValidateApprovedCredit;
import com.CrediFlow.CreditAnalysis.validation.CreditAnalysis.ValidateFinancialIssues;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreditAnalysisService {

    private final CreditAnalysisRepository creditAnalysisRepository;
    private final ValidateApprovedCredit validateApprovedCredit;
    private final ValidateFinancialIssues validateFinancialIssues;
    private final KafkaTemplate<String, CreditAnalysis> kafkaTemplate;
    private final LimiteClient limiteClient;
    private final ScoreClient scoreClient;

    public void analyze(Order order) {
        var score = scoreClient.validateScore();
        var financialIssue = validateFinancialIssues.validate();
        var approvedCredit = validateApprovedCredit.validate(financialIssue);
        var limite = limiteClient.limit(approvedCredit, score);

        var creditAnalysis = toCreditAnalysis(order, score, approvedCredit, financialIssue, limite);
        creditAnalysisRepository.save(creditAnalysis);

        if (!approvedCredit){
            throw new RuntimeException("Crédito não aprovado");
        }
        kafkaTemplate.send("credit-analysis-completed", creditAnalysis);
    }

    private CreditAnalysis toCreditAnalysis(Order order, Integer score,
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
