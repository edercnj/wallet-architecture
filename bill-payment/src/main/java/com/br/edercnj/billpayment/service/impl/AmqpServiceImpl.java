package com.br.edercnj.billpayment.service.impl;

import com.br.edercnj.billpayment.amqp.AmqpConfiguration;
import com.br.edercnj.billpayment.model.entity.FinancialMovement;
import com.br.edercnj.billpayment.service.AmqpService;
import org.springframework.stereotype.Service;

@Service
public class AmqpServiceImpl implements AmqpService {

    private final AmqpConfiguration<FinancialMovement> amqpConfiguration;

    public AmqpServiceImpl(AmqpConfiguration<FinancialMovement> amqpConfiguration)
    {this.amqpConfiguration = amqpConfiguration;}


    @Override
    public void sendFinancialMovementToConsumers(FinancialMovement financialMovement) {
        amqpConfiguration.producer(financialMovement);
    }
}
