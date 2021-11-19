package com.br.edercnj.walletuser.application.domain.entities;

import lombok.Data;

public class Withdraw {

    private String username;
    private Double amountToWithdraw;

    public Withdraw(String username, Double amountToWithdraw) {
        this.username = username;
        this.amountToWithdraw = amountToWithdraw;
    }

    public Withdraw() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getAmountToWithdraw() {
        return amountToWithdraw;
    }

    public void setAmountToWithdraw(Double amountToWithdraw) {
        this.amountToWithdraw = amountToWithdraw;
    }
}
