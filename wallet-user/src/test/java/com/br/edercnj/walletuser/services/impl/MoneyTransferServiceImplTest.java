package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.application.domain.entities.*;
import com.br.edercnj.walletuser.application.domain.service.UserService;
import com.br.edercnj.walletuser.application.services.*;
import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.mocks.FinancialMovementMock;
import com.br.edercnj.walletuser.mocks.UserMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class MoneyTransferServiceImplTest {

    @Autowired
    private MoneyTransferService moneyTransferService;
    @MockBean
    private WithdrawService withdrawService;
    @MockBean
    private DepositService depositService;
    @MockBean
    private UserService userService;
    @MockBean
    private FinancialMovementService financialMovementService;
    @MockBean
    private AmqpService amqpService;

    @BeforeEach
    void setUp() throws UserNotFoundException, InsufficientFundsException {
        when(userService.findUserByUsername(anyString())).thenReturn(UserMock.createUser());
        when(userService.depositInWallet(any(User.class), any(BigDecimal.class))).thenReturn(new BigDecimal(10));
        when(depositService.deposit(any(Deposit.class))).thenReturn(FinancialMovementMock.createFinancialMovement());
        when(withdrawService.withdraw(any(Withdraw.class))).thenReturn(FinancialMovementMock.createFinancialMovement());
        when(financialMovementService.createFinancialMovement(any(FinancialMovement.class))).thenReturn(FinancialMovementMock.createFinancialMovement());
        doNothing().when(amqpService).sendFinancialMovementToConsumers(any(FinancialMovement.class));
    }

    @Test
    void moneyTransfer_should_be_call_all_services() throws UserNotFoundException, InsufficientFundsException {
        MoneyTransfer moneyTransfer = new MoneyTransfer();
        moneyTransfer.setUserFrom("fulano");
        moneyTransfer.setUserTo("beltrano");
        moneyTransfer.setMoneyTransferAmount(10.00);
        moneyTransferService.moneyTransfer(moneyTransfer);
        verify(userService, times(1)).findUserByUsername(anyString());
        verify(depositService, times(1)).deposit(any(Deposit.class));
        verify(withdrawService, times(1)).withdraw(any(Withdraw.class));
        verify(financialMovementService, times(1)).createFinancialMovement(any(FinancialMovement.class));
        verify(amqpService, times(1)).sendFinancialMovementToConsumers(any(FinancialMovement.class));
    }
}