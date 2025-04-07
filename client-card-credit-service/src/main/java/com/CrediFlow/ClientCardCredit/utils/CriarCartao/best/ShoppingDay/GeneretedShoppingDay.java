package com.CrediFlow.ClientCardCredit.utils.CriarCartao.best.ShoppingDay;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class GeneretedShoppingDay implements GenereteExpirationPayment {

    @Override
    public Integer generated() {
        log.info("Inicializado o metodo [GeneretedShoppingDay - generated]");
        Random random = new Random();
        int min = 1;
        int max = 28;
        int result = random.nextInt(max - min + 1) + min;
        log.info("Melhor dia para compra gerado: {}", result);
        log.info("Finalizado o metodo [GeneretedShoppingDay - generated]");
        return result;
    }
}
