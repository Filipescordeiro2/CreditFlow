package com.CrediFlow.CreditAnalysis.validation.CreditAnalysis;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class ValidateApprovedCredit implements CreditAnalysisValidator {

    @Override
    public boolean validate(boolean financialIssue) {
        Random random = new Random();
        if (financialIssue) {
            return false;
        }
        return true;
    }
}
