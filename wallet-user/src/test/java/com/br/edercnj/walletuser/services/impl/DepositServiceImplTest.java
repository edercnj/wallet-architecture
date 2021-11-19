package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.mocks.FinancialMovementMock;
import com.br.edercnj.walletuser.mocks.UserMock;
import com.br.edercnj.walletuser.application.domain.entities.Deposit;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.services.AmqpService;
import com.br.edercnj.walletuser.application.services.DepositService;
import com.br.edercnj.walletuser.application.services.FinancialMovementService;
import com.br.edercnj.walletuser.application.services.UserService;
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
class DepositServiceImplTest {
    @Autowired
    private DepositService depositService;
    @MockBean
    private UserService userService;
    @MockBean
    private FinancialMovementService financialMovementService;
    @MockBean
    private AmqpService amqpService;

    @BeforeEach
    void setUp() throws UserNotFoundException {
        when(userService.findUserByUsername(anyString())).thenReturn(UserMock.createUser());
        when(userService.depositInWallet(any(User.class), any(BigDecimal.class))).thenReturn(new BigDecimal(10));
        when(financialMovementService.createFinancialMovement(any(FinancialMovement.class))).thenReturn(FinancialMovementMock.createFinancialMovement());
        doNothing().when(amqpService).sendFinancialMovementToConsumers(any(FinancialMovement.class));
    }

    @Test
    void deposit_should_be_call_all_services() throws UserNotFoundException {
        Deposit deposit = new Deposit();
        deposit.setAmountToDeposit(500.00);
        deposit.setUsername("fulano@fulano.com.br");
        depositService.deposit(deposit);
        verify(userService, times(1)).findUserByUsername(anyString());
        verify(userService, times(1)).depositInWallet(any(User.class), any(BigDecimal.class));
        verify(financialMovementService, times(1)).createFinancialMovement(any(FinancialMovement.class));
        verify(amqpService, times(1)).sendFinancialMovementToConsumers(any(FinancialMovement.class));
    }
}