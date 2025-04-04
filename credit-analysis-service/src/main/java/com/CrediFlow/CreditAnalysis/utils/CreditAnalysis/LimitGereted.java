package com.CrediFlow.CreditAnalysis.utils.CreditAnalysis;

import java.math.BigDecimal;

public interface LimitGereted {
    BigDecimal limit(Boolean approvedCredit, Integer scoreClien);
}
