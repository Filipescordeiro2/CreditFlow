package com.CrediFlow.ClientCardCredit.utils.CriarCartao.cvv;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class GeneretedCVVImp implements GeneretedCVV{

    @Override
    public String generateCVV() {
        log.info("Inicializado o metodo [GeneretedCVVImp - generateCVV]");

        Random random = new Random();
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(10));
        }
        log.info("Gerado o CVV: {}",cvv);
        log.info("Finalizado o metodo [GeneretedCVVImp - generateCVV]");
        return cvv.toString();
    }
}
