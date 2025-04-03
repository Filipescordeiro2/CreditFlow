package com.CrediFlow.OrderAnalyze.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {

    @Id
    private String id;
    private String cpf;
    private String nameClient;

    private boolean isValidCredit;
    private LocalDateTime creatAt;
    private LocalDateTime updateAt;

}
