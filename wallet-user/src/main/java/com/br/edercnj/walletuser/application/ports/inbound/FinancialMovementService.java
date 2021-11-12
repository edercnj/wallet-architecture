package com.br.edercnj.walletuser.application.ports.inbound;

import com.br.edercnj.walletuser.application.domain.entites.FinancialMovement;

import java.util.List;

public interface FinancialMovementService {
    FinancialMovement createFinancialMovement(FinancialMovement financialMovement);
    List<FinancialMovement> findFinancialMovementsByUsername(String username);
}
