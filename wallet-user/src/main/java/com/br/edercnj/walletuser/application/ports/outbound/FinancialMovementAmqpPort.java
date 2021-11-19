package com.br.edercnj.walletuser.application.ports.outbound;

import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;

public interface FinancialMovementAmqpPort{
    void producer(FinancialMovement financialMovement);
}
