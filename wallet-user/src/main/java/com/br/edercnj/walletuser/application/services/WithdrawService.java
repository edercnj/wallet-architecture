package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.domain.entities.Withdraw;

public interface WithdrawService {

    FinancialMovement withdraw(Withdraw withdraw) throws UserNotFoundException, InsufficientFundsException;
}
