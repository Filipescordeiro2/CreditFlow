package com.CrediFlow.ClientCardCredit.utils.Listener;

import com.CrediFlow.ClientCardCredit.domain.CardClient;
import com.CrediFlow.ClientCardCredit.model.CreditAnalysis;
import com.CrediFlow.ClientCardCredit.repository.CardClientRepository;
import com.CrediFlow.ClientCardCredit.utils.CriarCartao.best.ShoppingDay.GeneretedShoppingDay;
import com.CrediFlow.ClientCardCredit.utils.CriarCartao.best.expirationPayment.GeneretedExpiration;
import com.CrediFlow.ClientCardCredit.utils.CriarCartao.cardNumber.GeneretedCardNumberImp;
import com.CrediFlow.ClientCardCredit.utils.CriarCartao.cvv.GeneretedCVVImp;
import com.CrediFlow.ClientCardCredit.utils.CriarCartao.expirationDate.ExpirationDateImp;
import com.CrediFlow.ClientCardCredit.utils.CriarCartao.password.GeneretedPassword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@Slf4j
@RequiredArgsConstructor
public class CardClientCreationImpl implements CardClientCreation {

    private final CardClientRepository cardClientRepository;
    private final ExpirationDateImp expirationDateImp;
    private final GeneretedCVVImp generetedCVVImp;
    private final GeneretedCardNumberImp generetedCardNumberImp;
    private final GeneretedShoppingDay generetedShoppingDay;
    private final GeneretedExpiration generetedExpiration;
    private final GeneretedPassword generetedPassword;

    @Override
    public void generateCard(CreditAnalysis creditAnalysis) {
        if (creditAnalysis.getLimitApproved().compareTo(BigDecimal.ZERO) <= 0) {
            log.warn("Limite de crédito não aprovado. Cancelando a geração do cartão.");
            return;
        }

        var bestShoppingDay = generetedShoppingDay.generated();
        var cardClient = CardClient.builder()
                .cpf(creditAnalysis.getOrder().getCpf())
                .nameClient(creditAnalysis.getOrder().getNameClient())
                .handleName(creditAnalysis.getOrder().getHandleName())
                .cvv(generetedCVVImp.generateCVV())
                .cardNumber(generetedCardNumberImp.generateCardNumber())
                .expirationDate(expirationDateImp.generateExpirationDate())
                .bestShoppingDay(bestShoppingDay)
                .expirationPayment(generetedExpiration.generated(bestShoppingDay))
                .creditLimit(creditAnalysis.getLimitApproved())
                .password(generetedPassword.generatePassword())
                .build();

        cardClientRepository.save(cardClient);
        log.info("Cartão gerado e salvo no banco de dados: {}", cardClient);
    }
}
