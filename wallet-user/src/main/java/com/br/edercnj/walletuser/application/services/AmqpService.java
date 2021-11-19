package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;

public interface AmqpService {

    void sendFinancialMovementToConsumers(FinancialMovement financialMovement);
}
