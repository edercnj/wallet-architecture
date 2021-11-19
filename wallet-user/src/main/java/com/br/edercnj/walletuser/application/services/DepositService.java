package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.application.domain.entities.Deposit;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;

public interface DepositService {

    FinancialMovement deposit(Deposit deposit) throws UserNotFoundException;
}
