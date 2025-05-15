package com.CrediFlow.CreditAnalysis.utils.creditAnalysis;

import java.math.BigDecimal;

public interface LimitGereted {
    BigDecimal limit(Boolean approvedCredit, Integer scoreClien);
}
