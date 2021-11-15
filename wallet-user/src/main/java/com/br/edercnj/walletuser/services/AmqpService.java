package com.br.edercnj.walletuser.services;

import com.br.edercnj.walletuser.model.entities.FinancialMovement;

public interface AmqpService {

    void sendFinancialMovementToConsumers(FinancialMovement financialMovement);
}
