package com.CrediFlow.CreditAnalysis.utils.creditAnalysis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Slf4j
@RequiredArgsConstructor
public class LimiteClient implements LimitGereted{

    @Override
    public BigDecimal limit(Boolean approvedCredit, Integer scoreClien) {
        if (!approvedCredit) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        if (scoreClien >= 0 && scoreClien <= 120) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        } else if (scoreClien >= 121 && scoreClien <= 200) {
            return BigDecimal.valueOf(50 + Math.random() * (280 - 50)).setScale(2, RoundingMode.HALF_UP);
        } else if (scoreClien >= 201 && scoreClien <= 350) {
            return BigDecimal.valueOf(281 + Math.random() * (450 - 281)).setScale(2, RoundingMode.HALF_UP);
        } else if (scoreClien >= 351 && scoreClien <= 500) {
            return BigDecimal.valueOf(451 + Math.random() * (700 - 451)).setScale(2, RoundingMode.HALF_UP);
        } else if (scoreClien >= 501 && scoreClien <= 700) {
            return BigDecimal.valueOf(701 + Math.random() * (950 - 701)).setScale(2, RoundingMode.HALF_UP);
        } else if (scoreClien >= 701 && scoreClien <= 950) {
            return BigDecimal.valueOf(951 + Math.random() * (1200 - 951)).setScale(2, RoundingMode.HALF_UP);
        } else if (scoreClien >= 951 && scoreClien <= 1000) {
            return BigDecimal.valueOf(1201 + Math.random() * (250000 - 1201)).setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

}
