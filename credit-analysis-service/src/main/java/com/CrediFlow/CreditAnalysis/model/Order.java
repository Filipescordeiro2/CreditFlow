package com.CrediFlow.CreditAnalysis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private String cpf;
    private String nameClient;

    private boolean isValidCredit;
    private LocalDateTime creatAt;
    private LocalDateTime updateAt;
}
