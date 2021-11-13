package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.services.AmqpService;
import org.springframework.stereotype.Service;

@Service
public class AmqpServiceImpl implements AmqpService {

    @Override
    public void sendFinancialMovementToConsumers(FinancialMovement configurationRequest) {

    }
}
