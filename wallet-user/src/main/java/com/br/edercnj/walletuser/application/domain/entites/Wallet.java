package com.br.edercnj.walletuser.application.domain.entites;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class Wallet {

    private BigDecimal balance;

    public Wallet(BigDecimal balance) {
        this.balance = balance;
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
