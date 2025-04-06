package com.CrediFlow.ClientCardCredit.domain;

import com.CrediFlow.ClientCardCredit.enums.BlockingReason;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardClient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String cpf;
    private String nameClient;
    private String handleName;
    private String cvv;
    private String cardNumber;
    private String expirationDate;
    private String password;
    private Integer expirationPayment;
    private Integer bestShoppingDay;
    private BigDecimal creditLimit;


    @Enumerated(EnumType.STRING)
    private BlockingReason blockingReason;
    private String blockingDescription;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean isIssued;
    private boolean isActive;

    @PrePersist
    public void PrePersist() {
        this.isIssued = true;
        this.isActive = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void PreUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
