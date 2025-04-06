package com.CrediFlow.ClientCardCredit.utils.expirationDate;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExpirationDateImp implements GeneratedExpirationDate{

    @Override
    public String generateExpirationDate() {
        log.info("Inicializado o metodo [ExpirationDateImp - generateExpirationDate]");
        Random random = new Random();

        int month = random.nextInt(12) + 1;
        int currentYear = LocalDate.now().getYear() % 100;
        int year = currentYear + random.nextInt(10);
        String formattedDate = String.format("%02d/%02d", month, year);

        log.info("Generated expiration date: {}", formattedDate);
        log.info("Finalizado o metodo [ExpirationDateImp - generateExpirationDate]");
        return formattedDate;
    }
}
