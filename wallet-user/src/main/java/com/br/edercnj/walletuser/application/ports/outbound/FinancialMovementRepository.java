package com.br.edercnj.walletuser.application.ports.outbound;

import com.br.edercnj.walletuser.application.domain.entites.FinancialMovement;

import java.util.List;

public interface FinancialMovementRepository {
    FinancialMovement createFinancialMovement(FinancialMovement financialMovement);
    List<FinancialMovement> findFinancialMovementsByUsername(String username);
}
