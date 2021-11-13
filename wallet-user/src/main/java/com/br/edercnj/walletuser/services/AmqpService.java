package com.br.edercnj.walletuser.services;

import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import org.springframework.amqp.core.Message;

public interface AmqpService {

    void sendFinancialMovementToConsumers(FinancialMovement configurationRequest);
}
