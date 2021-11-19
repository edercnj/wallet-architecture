package com.br.edercnj.walletuser.application.domain.entities;


import lombok.Data;

public class Deposit {
    private String username;
    private Double amountToDeposit;


    public Deposit(String username, Double amountToDeposit) {
        this.username = username;
        this.amountToDeposit = amountToDeposit;
    }

    public Deposit() {
    }

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
