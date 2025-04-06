package com.CrediFlow.ClientCardCredit.listener;

import com.CrediFlow.ClientCardCredit.domain.CardClient;
import com.CrediFlow.ClientCardCredit.model.CreditAnalysis;
import com.CrediFlow.ClientCardCredit.repository.CardClientRepository;
import com.CrediFlow.ClientCardCredit.utils.best.ShoppingDay.GeneretedShoppingDay;
import com.CrediFlow.ClientCardCredit.utils.best.expirationPayment.GeneretedExpiration;
import com.CrediFlow.ClientCardCredit.utils.cardNumber.GeneretedCardNumberImp;
import com.CrediFlow.ClientCardCredit.utils.cvv.GeneretedCVVImp;
import com.CrediFlow.ClientCardCredit.utils.expirationDate.ExpirationDateImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeneretedCardClientListener {

    private final CardClientRepository cardClientRepository;
    private final ExpirationDateImp expirationDateImp;
    private final GeneretedCVVImp generetedCVVImp;
    private final GeneretedCardNumberImp generetedCardNumberImp;
    private final GeneretedShoppingDay generetedShoppingDay;
    private final GeneretedExpiration generetedExpiration;

    @KafkaListener(topics = "credit-analysis-completed", groupId = "client-card-credit-group")
    public void generetedCard(CreditAnalysis creditAnalysis){
        log.info("Inicializado o listener [GeneretedCardClientListener - generetedCard ]");
        log.info("Análise de crédito recebida: {}", creditAnalysis);

        var bestShoppingDay = generetedShoppingDay.generated();
        var cardClient = CardClient
                .builder()
                .cpf(creditAnalysis.getOrder().getCpf())
                .nameClient(creditAnalysis.getOrder().getNameClient())
                .handleName(creditAnalysis.getOrder().getNameClient())
                .cvv(generetedCVVImp.generateCVV())
                .cardNumber(generetedCardNumberImp.generateCardNumber())
                .expirationDate(expirationDateImp.generateExpirationDate())
                .bestShoppingDay(bestShoppingDay)
                .expirationPayment(generetedExpiration.generated(bestShoppingDay))
                .creditLimit(creditAnalysis.getLimitApproved())
                .build();

        cardClientRepository.save(cardClient);

        log.info("Cartão gerado e salvo no banco de dados: {}", cardClient);
        log.info("Finalizado o listener [GeneretedCardClientListener - generetedCard ]");
    }
}
