package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.entites.FinancialMovement;
import com.br.edercnj.walletuser.application.ports.inbound.FinancialMovementService;
import com.br.edercnj.walletuser.application.ports.outbound.FinancialMovementRepository;

import java.util.List;

public class FinancialMovementServiceImp implements FinancialMovementService {

    private final FinancialMovementRepository financialMovementRepository;

    public FinancialMovementServiceImp(FinancialMovementRepository financialMovementRepository) {
        this.financialMovementRepository = financialMovementRepository;
    }

    @Override
    public FinancialMovement createFinancialMovement(FinancialMovement financialMovement) {
        return financialMovementRepository.createFinancialMovement(financialMovement);
    }

    @Override
    public List<FinancialMovement> findFinancialMovementsByUsername(String username) {
        return financialMovementRepository.findFinancialMovementsByUsername(username);
    }
}
