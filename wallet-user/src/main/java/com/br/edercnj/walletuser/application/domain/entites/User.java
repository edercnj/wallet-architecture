package com.br.edercnj.walletuser.application.domain.entites;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class User {
    private String username;
    private String password;
    private String name;
    private Wallet wallet;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.wallet = new Wallet(new BigDecimal(0));
    }

    public User(String username, String password, String name, BigDecimal initialBalance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.wallet = new Wallet(initialBalance);
    }

    public BigDecimal depositInWallet(BigDecimal valueToDeposit) {
        return wallet.deposit(valueToDeposit);
    }

    public BigDecimal withdrawInWallet(BigDecimal valueToWithdraw) throws InsufficientFundsException {
        return wallet.withdraw(valueToWithdraw);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
