package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.domain.entities.MoneyTransfer;

public interface MoneyTransferService {

    FinancialMovement moneyTransfer(MoneyTransfer moneyTransfer) throws UserNotFoundException, InsufficientFundsException;
}
