package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.model.entities.FinancialMovementType;

import java.math.BigDecimal;

public class FinancialMovementMock {

    public static FinancialMovement createFinancialMovement()
    {
        return new FinancialMovement(FinancialMovementType.DEPOSIT, "123456", new BigDecimal(500));
    }
}
