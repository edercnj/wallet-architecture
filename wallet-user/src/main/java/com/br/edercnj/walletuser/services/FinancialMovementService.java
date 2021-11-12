package com.br.edercnj.walletuser.services;

import com.br.edercnj.walletuser.model.entities.FinancialMovement;

import java.util.List;

public interface FinancialMovementService {
    FinancialMovement createFinancialMovement(FinancialMovement financialMovement);

    List<FinancialMovement> findAll();
}
