package com.br.edercnj.walletuser.model.dto;

import lombok.Data;

@Data
public class DepositDto {
    private String username;
    private Float amountToDeposit;
}
