package com.br.edercnj.walletuser.web.api.v1;

import com.br.edercnj.walletuser.adapters.inbound.web.api.v1.WithdrawController;
import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.mocks.FinancialMovementMock;
import com.br.edercnj.walletuser.mocks.WithdrawMock;
import com.br.edercnj.walletuser.application.domain.entities.Withdraw;
import com.br.edercnj.walletuser.application.services.WithdrawService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(WithdrawController.class)
class WithdrawControllerTest {

    @MockBean
    private  WithdrawService withdrawService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws UserNotFoundException, InsufficientFundsException {

        Mockito.when(withdrawService.withdraw(any(Withdraw.class))).thenReturn(FinancialMovementMock.createFinancialMovement());
    }

    @Test
    void withdraw_should_be_return_201() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/wallets/withdraws")
                        .content(new ObjectMapper().writeValueAsString(WithdrawMock.createWithdraw()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.movementIdentifier").exists());
    }
}