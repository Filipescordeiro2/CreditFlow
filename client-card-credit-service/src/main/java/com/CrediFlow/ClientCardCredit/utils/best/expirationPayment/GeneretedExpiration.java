package com.CrediFlow.ClientCardCredit.utils.best.expirationPayment;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class GeneretedExpiration implements GenereteExpiration {

    private static final int DIAS_ANTECEDENCIA = 8;
    private static final int MIN_EXPIRATION_DAY = 1;

    @Override
    public Integer generated(Integer shoppingDay) {
        log.info("Inicializado o metodo [GeneretedExpiration - generated]");

        int bestDay = shoppingDay - DIAS_ANTECEDENCIA;
        bestDay = Math.max(bestDay, MIN_EXPIRATION_DAY); // garante que não será negativo ou zero

        log.info("Melhor dia para pagamento gerado: {}", bestDay);
        log.info("Finalizado o metodo [GeneretedExpiration - generated]");

        return bestDay;
    }
}
