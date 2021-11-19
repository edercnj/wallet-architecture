package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.adapters.dto.DepositDto;

public class DepositDtoMock {

    public static DepositDto createDepositDto() {
        DepositDto dto = new DepositDto();
        dto.setAmountToDeposit(100.00);
        dto.setUsername("teste");
        return dto;
    }
}
