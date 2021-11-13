package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.model.entities.FinancialMovementType;
import com.br.edercnj.walletuser.repository.FinancialMovementRepository;
import com.br.edercnj.walletuser.services.FinancialMovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class FinancialMovementServiceImplTest {

    @MockBean
    private FinancialMovementRepository financialMovementRepository;

    @Autowired
    private FinancialMovementService financialMovementService;

    private FinancialMovement financialMovementMock;

    @BeforeEach
    void setUp() {
        financialMovementMock = new FinancialMovement(FinancialMovementType.DEPOSIT, "123456", new BigDecimal(500));
        when(financialMovementRepository.save(any(FinancialMovement.class))).thenReturn(financialMovementMock);
        when(financialMovementRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    void createFinancialMovement_should_be_invoke_financialMovementRepository_save() {
        financialMovementService.createFinancialMovement(new FinancialMovement());
        verify(financialMovementRepository, times(1)).save(any(FinancialMovement.class));
    }

    @Test
    void findAll_should_be_invoke_financialMovementRepository_save() {
        financialMovementService.findAll();
        verify(financialMovementRepository, times(1)).findAll();
    }

    @Test
    void findAll() {
    }
}