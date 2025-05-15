package com.CrediFlow.CreditAnalysis.validation.creditAnalysis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class ValidateFinancialIssues implements CreditAnalysisFinanciallIssues {

    @Override
    public boolean validate( ) {
        Random random = new Random();
        boolean randomValue = random.nextBoolean();
        return randomValue;
    }
}
