package com.br.edercnj.walletuser.services;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import  com.br.edercnj.walletuser.model.entities.MoneyTransfer;

public interface MoneyTransferService {

    FinancialMovement moneyTransfer(MoneyTransfer moneyTransfer) throws UserNotFoundException, InsufficientFundsException;
}
