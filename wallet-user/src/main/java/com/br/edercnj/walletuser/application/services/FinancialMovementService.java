package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;

import java.util.List;

public interface FinancialMovementService {
    FinancialMovement createFinancialMovement(FinancialMovement financialMovement);

    List<FinancialMovement> findAll();
}
