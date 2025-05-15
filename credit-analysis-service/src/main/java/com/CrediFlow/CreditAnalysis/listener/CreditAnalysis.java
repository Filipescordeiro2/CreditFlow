package com.CrediFlow.CreditAnalysis.listener;

import com.CrediFlow.CreditAnalysis.config.template.KafkaMessagePublisher;
import com.CrediFlow.CreditAnalysis.mapper.CreditAnalysisMapper;
import com.CrediFlow.CreditAnalysis.model.Order;
import com.CrediFlow.CreditAnalysis.repository.CreditAnalysisRepository;
import com.CrediFlow.CreditAnalysis.utils.creditAnalysis.LimiteClient;
import com.CrediFlow.CreditAnalysis.utils.creditAnalysis.ScoreClient;
import com.CrediFlow.CreditAnalysis.validation.creditAnalysis.ValidateApprovedCredit;
import com.CrediFlow.CreditAnalysis.validation.creditAnalysis.ValidateFinancialIssues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CreditAnalysis {

    private final CreditAnalysisRepository creditAnalysisRepository;
    private final ValidateApprovedCredit validateApprovedCredit;
    private final ValidateFinancialIssues validateFinancialIssues;
    private final LimiteClient limiteClient;
    private final ScoreClient scoreClient;
    private final KafkaMessagePublisher kafkaMessagePublisher;

    public void analyze(Order order) {
        var score = scoreClient.validateScore();
        var financialIssue = validateFinancialIssues.validate();
        var approvedCredit = validateApprovedCredit.validate(financialIssue);
        var limite = limiteClient.limit(approvedCredit, score);

        var creditAnalysis = CreditAnalysisMapper.toCreditAnalysis(order, score, approvedCredit, financialIssue, limite);
        creditAnalysisRepository.save(creditAnalysis);

        if (!approvedCredit || limite.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Crédito não aprovado");
        }
        kafkaMessagePublisher.publish("order-status-updated",order);
        kafkaMessagePublisher.publish("credit-analysis-completed",creditAnalysis);
    }

}
