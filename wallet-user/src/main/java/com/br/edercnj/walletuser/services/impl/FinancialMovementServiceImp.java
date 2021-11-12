package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.repository.FinancialMovementRepository;
import com.br.edercnj.walletuser.services.FinancialMovementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialMovementServiceImp implements FinancialMovementService {


    private final FinancialMovementRepository financialMovementRepository;

    public FinancialMovementServiceImp(FinancialMovementRepository financialMovementRepository) {this.financialMovementRepository = financialMovementRepository;}

    @Override
    public FinancialMovement createFinancialMovement(FinancialMovement financialMovement) {
        return financialMovementRepository.save(financialMovement);
    }

    @Override
    public List<FinancialMovement> findAll() {
        return financialMovementRepository.findAll();
    }
}
