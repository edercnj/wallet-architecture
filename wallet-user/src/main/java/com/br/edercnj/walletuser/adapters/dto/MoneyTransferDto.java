package com.br.edercnj.walletuser.adapters.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MoneyTransferDto {
    @NotNull
    private Double moneyTransferAmount;
    @NotBlank
    private String userTo;
    @NotBlank
    private String userFrom;
}
