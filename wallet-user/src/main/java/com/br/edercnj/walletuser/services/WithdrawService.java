package com.br.edercnj.walletuser.services;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.model.entities.Withdraw;

public interface WithdrawService {

    FinancialMovement withdraw(Withdraw withdraw) throws UserNotFoundException, InsufficientFundsException;
}
