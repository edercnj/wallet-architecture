package com.br.edercnj.walletuser.model.entities;


public class Deposit {
    private String username;
    private Double amountToDeposit;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getAmountToDeposit() {
        return amountToDeposit;
    }

    public void setAmountToDeposit(Double amountToDeposit) {
        this.amountToDeposit = amountToDeposit;
    }
}
