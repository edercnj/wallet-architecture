package com.br.edercnj.walletuser.model.entities;

import lombok.Data;


@Data
public class MoneyTransfer {
    private Double moneyTransferAmount;
    private String userTo;
    private String userFrom;
}
