package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.model.entities.MoneyTransfer;

public class MoneyTransferMock {

    public static MoneyTransfer createMoneyTransfer() {
        MoneyTransfer moneyTransfer = new MoneyTransfer();
        moneyTransfer.setMoneyTransferAmount(100.00);
        moneyTransfer.setUserTo("teste");
        moneyTransfer.setUserFrom("teste1");
        return moneyTransfer;
    }
}
