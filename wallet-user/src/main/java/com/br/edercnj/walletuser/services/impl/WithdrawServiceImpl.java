package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.entities.Deposit;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.services.AmqpService;
import com.br.edercnj.walletuser.services.FinancialMovementService;
import com.br.edercnj.walletuser.services.UserService;
import com.br.edercnj.walletuser.services.WithdrawService;
import lombok.With;

public class WithdrawServiceImpl implements WithdrawService {

    private final UserService userService;
    private final FinancialMovementService financialMovementService;
    private final AmqpService amqpService;

    public WithdrawServiceImpl(UserService userService, FinancialMovementService financialMovementService, AmqpService amqpService) {
        this.userService = userService;
        this.financialMovementService = financialMovementService;
        this.amqpService = amqpService;
    }

    @Override
    public FinancialMovement withdraw(Deposit deposit) throws UserNotFoundException {
        return null;
    }
}
