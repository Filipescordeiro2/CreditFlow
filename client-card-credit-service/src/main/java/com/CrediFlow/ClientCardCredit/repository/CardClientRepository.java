package com.CrediFlow.ClientCardCredit.repository;

import com.CrediFlow.ClientCardCredit.domain.CardClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardClientRepository extends JpaRepository<CardClient, UUID> {
    boolean existsByCpfAndIsActiveTrue(String cpf);
    boolean existsByCpfAndIsIssuedTrue(String cpf);

}
