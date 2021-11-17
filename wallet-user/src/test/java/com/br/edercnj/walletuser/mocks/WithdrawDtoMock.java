package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.model.dto.WithdrawDto;
import com.br.edercnj.walletuser.model.entities.Withdraw;

public class WithdrawDtoMock {

    public static WithdrawDto createWithdrawDto() {
        WithdrawDto withdraw = new WithdrawDto();
        withdraw.setAmountToWithdraw(100.00);
        withdraw.setUsername("eder");
        return withdraw;
    }
}
