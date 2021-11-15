package com.br.edercnj.walletuser.model.entities;


import lombok.Data;

@Data
public class Deposit {
    private String username;
    private Double amountToDeposit;


    public Deposit(String username, Double amountToDeposit) {
        this.username = username;
        this.amountToDeposit = amountToDeposit;
    }

    public Deposit() {
    }
}
