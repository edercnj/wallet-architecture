package com.br.edercnj.wallettimeline.service.impl;


import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;
import com.br.edercnj.wallettimeline.repository.FinancialMovementRepository;
import com.br.edercnj.wallettimeline.service.FinancialMovementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialMovementServiceImpl implements FinancialMovementService {

    private final FinancialMovementRepository financialMovementRepository;

    public FinancialMovementServiceImpl(FinancialMovementRepository financialMovementRepository) {this.financialMovementRepository = financialMovementRepository;}

    @Override
    public void createFinancialMovement(FinancialMovement financialMovement) {
        financialMovementRepository.save(financialMovement);
    }

    @Override
    public List<FinancialMovement> findByUserId(String userId) {
        return financialMovementRepository.findByUserId(userId);
    }

    @Override
    public List<FinancialMovement> findAll() {
        return financialMovementRepository.findAll();
    }
}
