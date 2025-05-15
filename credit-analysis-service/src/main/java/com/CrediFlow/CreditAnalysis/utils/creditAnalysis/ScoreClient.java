package com.CrediFlow.CreditAnalysis.utils.creditAnalysis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScoreClient implements ScoreClientGereted{

    @Override
    public Integer validateScore() {
        log.info("Inicializado o metodo [ScoreClient - validateScore ]");
        Random random = new Random();
        var score = random.nextInt(1001);

        log.info("Score Validado");
        log.info("Finalizando o metodo [ScoreClient - validateScore]");
        return score;
    }
}
