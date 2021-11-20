package com.br.edercnj.walletuser.application.services.impl;

import com.br.edercnj.walletuser.application.domain.entities.*;
import com.br.edercnj.walletuser.application.domain.entities.enums.FinancialMovementType;
import com.br.edercnj.walletuser.application.services.UserService;
import com.br.edercnj.walletuser.application.services.*;
import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final UserService userService;
    private final FinancialMovementService financialMovementService;
    private final AmqpService amqpService;

    public MoneyTransferServiceImpl(DepositService depositService, WithdrawService withdrawService, UserService userService, FinancialMovementService financialMovementService, AmqpService amqpService) {
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.userService = userService;
        this.financialMovementService = financialMovementService;
        this.amqpService = amqpService;
    }

    @Override
    public FinancialMovement moneyTransfer(MoneyTransfer moneyTransfer) throws UserNotFoundException, InsufficientFundsException {
        User userFrom = userService.findUserByUsername(moneyTransfer.getUserFrom());
        withdrawService.withdraw(new Withdraw(userFrom.getUsername(), moneyTransfer.getMoneyTransferAmount()));
        depositService.deposit(new Deposit(moneyTransfer.getUserTo(), moneyTransfer.getMoneyTransferAmount()));
        FinancialMovement financialMovement = new FinancialMovement(FinancialMovementType.MONEY_TRANSFER, userFrom.getId(), BigDecimal.valueOf(moneyTransfer.getMoneyTransferAmount()));
        financialMovementService.createFinancialMovement(financialMovement);
        amqpService.sendFinancialMovementToConsumers(financialMovement);
        return financialMovement;
    }
}
