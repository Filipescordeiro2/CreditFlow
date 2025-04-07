package com.CrediFlow.ClientCardCredit.utils.CriarCartao.password;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class GeneretedPassword implements EmissorPassword{

    @Override
    public String generatePassword() {
        log.info("Inicializado o metodo [GeneretedPassword - generatePassword]");
        Random random = new Random();
        StringBuilder passwordBuilder = new StringBuilder();
        // Gera uma senha numérica com 6 dígitos
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            passwordBuilder.append(digit);
        }
        log.info("Primeira Senha gerada: {}", passwordBuilder.toString());
        log.info("Finalizado o metodo [GeneretedPassword - generatePassword]");
        return passwordBuilder.toString();
    }

}
