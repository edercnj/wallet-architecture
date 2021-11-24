package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.mocks.FinancialMovementMock;
import com.br.edercnj.walletuser.mocks.UserMock;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.domain.entities.Withdraw;
import com.br.edercnj.walletuser.application.services.AmqpService;
import com.br.edercnj.walletuser.application.services.FinancialMovementService;
import com.br.edercnj.walletuser.application.domain.service.UserService;
import com.br.edercnj.walletuser.application.services.WithdrawService;
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
class WithdrawServiceImplTest {

    @Autowired
    private WithdrawService  withdrawService;
    @MockBean
    private UserService userService;
    @MockBean
    private FinancialMovementService financialMovementService;
    @MockBean
    private AmqpService amqpService;

    @BeforeEach
    void setUp() throws UserNotFoundException, InsufficientFundsException {

        when(userService.findUserByUsername(anyString())).thenReturn(UserMock.createUser());
        when(userService.withdrawInWallet(any(User.class), any(BigDecimal.class))).thenReturn(new BigDecimal(10));
        when(financialMovementService.createFinancialMovement(any(FinancialMovement.class))).thenReturn(FinancialMovementMock.createFinancialMovement());
        doNothing().when(amqpService).sendFinancialMovementToConsumers(any(FinancialMovement.class));
    }

    @Test
    void withdraw_should_be_call_all_services() throws UserNotFoundException, InsufficientFundsException {
        Withdraw deposit = new Withdraw();
        deposit.setAmountToWithdraw(500.00);
        deposit.setUsername("fulano@fulano.com.br");
        withdrawService.withdraw(deposit);
        verify(userService, times(1)).findUserByUsername(anyString());
        verify(userService, times(1)).withdrawInWallet(any(User.class), any(BigDecimal.class));
        verify(financialMovementService, times(1)).createFinancialMovement(any(FinancialMovement.class));
        verify(amqpService, times(1)).sendFinancialMovementToConsumers(any(FinancialMovement.class));
    }
}