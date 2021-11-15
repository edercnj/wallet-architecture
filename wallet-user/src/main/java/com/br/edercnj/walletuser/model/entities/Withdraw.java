package com.br.edercnj.walletuser.model.entities;

import lombok.Data;

@Data
public class Withdraw {

    private String username;
    private Double amountToWithdraw;

    public Withdraw(String username, Double amountToWithdraw) {
        this.username = username;
        this.amountToWithdraw = amountToWithdraw;
    }

    public Withdraw() {
    }
}
