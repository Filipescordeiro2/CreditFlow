package com.CrediFlow.ClientCardCredit.listener;

import com.CrediFlow.ClientCardCredit.model.CreditAnalysis;
import com.CrediFlow.ClientCardCredit.utils.Listener.CardClientCreation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneretedCardClientListener {

    private final CardClientCreation cardClientCreationService;

    @KafkaListener(topics = "credit-analysis-completed", groupId = "client-card-credit-group")
    public void generetedCard(CreditAnalysis creditAnalysis) {
        log.info("Inicializado o listener [GeneretedCardClientListener - generetedCard]");
        log.info("Análise de crédito recebida: {}", creditAnalysis);

        cardClientCreationService.generateCard(creditAnalysis);

        log.info("Finalizado o listener [GeneretedCardClientListener - generetedCard]");
    }
}

