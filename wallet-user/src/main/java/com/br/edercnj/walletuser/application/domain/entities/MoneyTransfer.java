package com.br.edercnj.walletuser.application.domain.entities;

import lombok.Data;


public class MoneyTransfer {
    private Double moneyTransferAmount;
    private String userTo;
    private String userFrom;


    public Double getMoneyTransferAmount() {
        return moneyTransferAmount;
    }

    public void setMoneyTransferAmount(Double moneyTransferAmount) {
        this.moneyTransferAmount = moneyTransferAmount;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }
}
