package com.CrediFlow.CreditAnalysis.utils.AnalyzeCredit;

import com.CrediFlow.CreditAnalysis.mapper.CreditAnalysisMapper;
import com.CrediFlow.CreditAnalysis.model.Order;
import com.CrediFlow.CreditAnalysis.repository.CreditAnalysisRepository;
import com.CrediFlow.CreditAnalysis.utils.CreditAnalysis.LimiteClient;
import com.CrediFlow.CreditAnalysis.utils.CreditAnalysis.ScoreClient;
import com.CrediFlow.CreditAnalysis.utils.KafkaTemplate.producerCreditAnalysis.MessagePublisherCreditAnalysis;
import com.CrediFlow.CreditAnalysis.utils.KafkaTemplate.producerOrder.KafkaMessagePublisherOrder;
import com.CrediFlow.CreditAnalysis.validation.CreditAnalysis.ValidateApprovedCredit;
import com.CrediFlow.CreditAnalysis.validation.CreditAnalysis.ValidateFinancialIssues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CreditAnalysisImpl implements AnalyzeCredit {

    private final CreditAnalysisRepository creditAnalysisRepository;
    private final ValidateApprovedCredit validateApprovedCredit;
    private final ValidateFinancialIssues validateFinancialIssues;
    private final LimiteClient limiteClient;
    private final ScoreClient scoreClient;
    private final MessagePublisherCreditAnalysis messagePublisherCreditAnalysis;
    private final KafkaMessagePublisherOrder messagePublisherOrder;

    @Override
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
        messagePublisherOrder.publish("order-status-updated",order);
        messagePublisherCreditAnalysis.publish("credit-analysis-completed",creditAnalysis);
    }

}
