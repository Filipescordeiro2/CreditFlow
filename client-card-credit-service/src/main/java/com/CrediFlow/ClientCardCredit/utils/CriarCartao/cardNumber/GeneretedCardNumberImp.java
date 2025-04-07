package com.CrediFlow.ClientCardCredit.utils.CriarCartao.cardNumber;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class GeneretedCardNumberImp implements GeneretedCardNumber {

    @Override
    public String generateCardNumber() {
        log.info("Inicializado o metodo [GeneretedCardNumberImp - generateCardNumber]");
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        log.info("Gerado o numero do cartao: {}", cardNumber);
        log.info("Finalizado o metodo [GeneretedCardNumberImp - generateCardNumber]");
        return cardNumber.toString();
    }
}
