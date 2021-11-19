package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.domain.entities.enums.FinancialMovementType;

import java.math.BigDecimal;

public class FinancialMovementMock {

    public static FinancialMovement createFinancialMovement()
    {
        return new FinancialMovement(FinancialMovementType.DEPOSIT, "123456", new BigDecimal(500));
    }
}
