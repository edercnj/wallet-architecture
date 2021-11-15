package com.br.edercnj.wallettimeline.service;


import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;

import java.util.List;

public interface FinancialMovementService {
    void createFinancialMovement(FinancialMovement financialMovement);

    List<FinancialMovement> findByUserId(String userId);

    List<FinancialMovement> findAll();
}
