package com.br.edercnj.walletuser.application.services.impl;

import com.br.edercnj.walletuser.application.ports.outbound.FinancialMovementAmqpPort;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.services.AmqpService;
import org.springframework.stereotype.Service;

@Service
public class AmqpServiceImpl implements AmqpService {

    private final FinancialMovementAmqpPort financialMovementAmqpPort;

    public AmqpServiceImpl(FinancialMovementAmqpPort financialMovementAmqpPort) {this.financialMovementAmqpPort = financialMovementAmqpPort;}


    @Override
    public void sendFinancialMovementToConsumers(FinancialMovement financialMovement) {
        financialMovementAmqpPort.producer(financialMovement);
    }
}
