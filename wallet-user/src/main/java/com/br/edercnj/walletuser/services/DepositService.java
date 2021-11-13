package com.br.edercnj.walletuser.services;

import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.entities.Deposit;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;

public interface DepositService {

    FinancialMovement deposit(Deposit deposit) throws UserNotFoundException;
}
