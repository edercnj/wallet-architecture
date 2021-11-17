package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.model.dto.MoneyTransferDto;

public class MoneyTransferDtoMock {

    public static MoneyTransferDto createMoneyTransferDto() {
        MoneyTransferDto dto = new MoneyTransferDto();
        dto.setMoneyTransferAmount(100.00);
        dto.setUserFrom("teste");
        dto.setUserTo("teste1");
        return dto;
    }
}
