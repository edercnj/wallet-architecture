package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.application.domain.entities.Withdraw;

public class WithdrawMock {

    public static Withdraw createWithdraw() {
        Withdraw withdraw = new Withdraw();
        withdraw.setAmountToWithdraw(100.00);
        withdraw.setUsername("eder");
        return withdraw;
    }
}
