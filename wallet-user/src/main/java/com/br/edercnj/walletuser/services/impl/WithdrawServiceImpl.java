package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.model.entities.FinancialMovementType;
import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.model.entities.Withdraw;
import com.br.edercnj.walletuser.services.AmqpService;
import com.br.edercnj.walletuser.services.FinancialMovementService;
import com.br.edercnj.walletuser.services.UserService;
import com.br.edercnj.walletuser.services.WithdrawService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
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
    public FinancialMovement withdraw(Withdraw withdraw) throws UserNotFoundException, InsufficientFundsException {
        User user = userService.findUserByUsername(withdraw.getUsername());
        userService.withdrawInWallet(user, BigDecimal.valueOf(withdraw.getAmountToWithdraw()));
        FinancialMovement financialMovement = financialMovementService.createFinancialMovement(new FinancialMovement(FinancialMovementType.WITHDRAW, user.getId(), BigDecimal.valueOf(withdraw.getAmountToWithdraw())));
        amqpService.sendFinancialMovementToConsumers(financialMovement);
        return financialMovement;
    }
}
