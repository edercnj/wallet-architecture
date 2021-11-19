package com.br.edercnj.walletuser.application.domain.entities;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;

import java.math.BigDecimal;


public class User {
    private String id;
    private String username;
    private String name;
    private Wallet wallet;

    public User(String username, String name) {
        this.username = username;
        this.name = name;
        this.wallet = new Wallet(new BigDecimal(0));
    }

    public User(String username, String name, BigDecimal initialBalance) {
        this.username = username;
        this.name = name;
        this.wallet = new Wallet(initialBalance);
    }

    public User() {
        this.wallet = new Wallet();
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Wallet getWallet() {return wallet;}

    public void setWallet(Wallet wallet) {this.wallet = wallet;}

    public BigDecimal depositInWallet(BigDecimal valueToDeposit) {
        return wallet.deposit(valueToDeposit);
    }

    public BigDecimal withdrawInWallet(BigDecimal valueToWithdraw) throws InsufficientFundsException {
        return wallet.withdraw(valueToWithdraw);
    }
}
