package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.model.entities.FinancialMovementType;
import com.br.edercnj.walletuser.model.entities.User;

import java.math.BigDecimal;

public class FinancialMovementMock {

    public static FinancialMovement createFinancialMovement()
    {
        return new FinancialMovement(FinancialMovementType.DEPOSIT, new User("edercnj", "Eder", new BigDecimal(500)), new BigDecimal(500));
    }
}
