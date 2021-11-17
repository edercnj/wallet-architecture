package com.br.edercnj.wallettimeline.amqp.impl;

import com.br.edercnj.wallettimeline.mocks.FinancialMovementMock;
import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;
import com.br.edercnj.wallettimeline.service.FinancialMovementService;
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
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class RabbitMQFinancialMovementConsumerTest {

    @MockBean
    private FinancialMovementService financialMovementService;

    @Autowired
    private RabbitMQFinancialMovementConsumer rabbitMQFinancialMovementConsumer;

    @BeforeEach
    void setUp() {
        Mockito.doNothing().when(financialMovementService).createFinancialMovement(any(FinancialMovement.class));
    }

    @Test
    void consumer() {
        rabbitMQFinancialMovementConsumer.consumer(FinancialMovementMock.createFinancialMovement());
        Mockito.verify(financialMovementService, times(1)).createFinancialMovement(any(FinancialMovement.class));
    }
}