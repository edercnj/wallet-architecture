package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.amqp.producer.AmqpConfiguration;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.services.AmqpService;
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
