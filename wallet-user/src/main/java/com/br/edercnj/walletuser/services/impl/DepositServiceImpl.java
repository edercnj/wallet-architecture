package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.entities.Deposit;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.model.entities.FinancialMovementType;
import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.services.AmqpService;
import com.br.edercnj.walletuser.services.DepositService;
import com.br.edercnj.walletuser.services.FinancialMovementService;
import com.br.edercnj.walletuser.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DepositServiceImpl implements DepositService {

    private final UserService userService;
    private final FinancialMovementService financialMovementService;
    private final AmqpService amqpService;

    public DepositServiceImpl(UserService userService, FinancialMovementService financialMovementService, AmqpService amqpService) {
        this.userService = userService;
        this.financialMovementService = financialMovementService;
        this.amqpService = amqpService;
    }

    @Override
    @Transactional
    public FinancialMovement deposit(Deposit deposit) throws UserNotFoundException {
        User user = userService.findUserByUsername(deposit.getUsername());
        userService.depositInWallet(user, BigDecimal.valueOf(deposit.getAmountToDeposit()));
        FinancialMovement financialMovement = financialMovementService.createFinancialMovement(new FinancialMovement(FinancialMovementType.DEPOSIT, user, BigDecimal.valueOf(deposit.getAmountToDeposit())));
        amqpService.sendFinancialMovementToConsumers(financialMovement);
        return financialMovement;
    }
}
