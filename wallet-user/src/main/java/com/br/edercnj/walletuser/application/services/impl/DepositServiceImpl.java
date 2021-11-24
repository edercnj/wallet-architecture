package com.br.edercnj.walletuser.application.services.impl;

import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.application.domain.entities.Deposit;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.domain.entities.enums.FinancialMovementType;
import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.services.AmqpService;
import com.br.edercnj.walletuser.application.services.DepositService;
import com.br.edercnj.walletuser.application.services.FinancialMovementService;
import com.br.edercnj.walletuser.application.domain.service.UserService;
import org.springframework.stereotype.Service;

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
    public FinancialMovement deposit(Deposit deposit) throws UserNotFoundException {
        User user = userService.findUserByUsername(deposit.getUsername());
        userService.depositInWallet(user, BigDecimal.valueOf(deposit.getAmountToDeposit()));
        FinancialMovement financialMovement = financialMovementService.createFinancialMovement(new FinancialMovement(FinancialMovementType.DEPOSIT, user.getId(), BigDecimal.valueOf(deposit.getAmountToDeposit())));
        amqpService.sendFinancialMovementToConsumers(financialMovement);
        return financialMovement;
    }
}
