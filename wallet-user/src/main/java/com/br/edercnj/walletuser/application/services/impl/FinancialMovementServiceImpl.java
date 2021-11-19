package com.br.edercnj.walletuser.application.services.impl;

import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.adapters.outbound.persistence.mongo.FinancialMovementSpringDataMongoRepository;
import com.br.edercnj.walletuser.application.services.FinancialMovementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialMovementServiceImpl implements FinancialMovementService {

    private final FinancialMovementSpringDataMongoRepository financialMovementSpringDataMongoRepository;

    public FinancialMovementServiceImpl(FinancialMovementSpringDataMongoRepository financialMovementSpringDataMongoRepository) {this.financialMovementSpringDataMongoRepository = financialMovementSpringDataMongoRepository;}

    @Override
    public FinancialMovement createFinancialMovement(FinancialMovement financialMovement) {
        return financialMovementSpringDataMongoRepository.save(financialMovement);
    }

    @Override
    public List<FinancialMovement> findAll() {
        return financialMovementSpringDataMongoRepository.findAll();
    }
}
