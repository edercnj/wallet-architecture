package com.br.edercnj.walletuser.model.entities;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class Wallet {

    private BigDecimal balance;

    public Wallet(BigDecimal balance) {
        this.balance = balance;
    }

    public Wallet() {
        this.balance = new BigDecimal(0);
    }

    public BigDecimal deposit(BigDecimal valueToDeposit) {
        this.balance = balance.add(valueToDeposit);
        return balance;
    }

    public BigDecimal withdraw(BigDecimal valueToWithdraw) throws InsufficientFundsException {
        if (isInsufficientFunds(this.balance, valueToWithdraw))
            throw new InsufficientFundsException();

        this.balance = balance.subtract(valueToWithdraw);
        return balance;
    }

    private boolean isInsufficientFunds(BigDecimal balance, BigDecimal valueToWithdraw) {
        return balance.compareTo(valueToWithdraw) < 0;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
