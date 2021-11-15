package com.br.edercnj.wallettimeline.amqp.impl;

import com.br.edercnj.wallettimeline.amqp.AmqpConsumer;
import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;

import com.br.edercnj.wallettimeline.service.FinancialMovementService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQFinancialMovementConsumer implements AmqpConsumer<FinancialMovement> {


    private final FinancialMovementService financialMovementService;

    public RabbitMQFinancialMovementConsumer(FinancialMovementService financialMovementService) {this.financialMovementService = financialMovementService;}

    @Override
    @RabbitListener(queues = "rk.wallettimeline")
    public void consumer(FinancialMovement message) {
        financialMovementService.createFinancialMovement(message);
    }
}
