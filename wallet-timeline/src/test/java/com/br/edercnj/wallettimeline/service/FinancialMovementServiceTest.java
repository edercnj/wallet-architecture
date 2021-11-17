package com.br.edercnj.wallettimeline.service;

import com.br.edercnj.wallettimeline.mocks.FinancialMovementMock;
import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;
import com.br.edercnj.wallettimeline.repository.FinancialMovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class FinancialMovementServiceTest {

    @Autowired
    private FinancialMovementService financialMovementService;
    @MockBean
    private FinancialMovementRepository financialMovementRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(financialMovementRepository.save(any(FinancialMovement.class))).thenReturn(FinancialMovementMock.createFinancialMovement());
        Mockito.when(financialMovementRepository.findByUserId(anyString())).thenReturn(Mockito.anyList());
    }

    @Test
    void createFinancialMovement() {
        financialMovementService.createFinancialMovement(FinancialMovementMock.createFinancialMovement());
        Mockito.verify(financialMovementRepository, Mockito.times(1)).save(any(FinancialMovement.class));
    }

    @Test
    void findByUserId() {
        financialMovementService.findByUserId("teste");
        Mockito.verify(financialMovementRepository, Mockito.times(1)).findByUserId(anyString());
    }

}