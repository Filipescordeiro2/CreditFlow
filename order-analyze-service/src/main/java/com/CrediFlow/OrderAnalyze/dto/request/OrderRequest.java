package com.CrediFlow.OrderAnalyze.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @CPF(message = "cpf is invalid")
    @NotBlank(message = "cpf is required")
    private String cpf;

    @NotBlank(message = "nameClient is required")
    private String nameClient;

    @NotBlank(message = "handleName is required")
    private String handleName;

}
