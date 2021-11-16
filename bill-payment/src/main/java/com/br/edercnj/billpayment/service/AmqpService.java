package com.br.edercnj.billpayment.service;

import com.br.edercnj.billpayment.model.entity.FinancialMovement;

public interface AmqpService {

    void sendFinancialMovementToConsumers(FinancialMovement financialMovement);
}
