package com.br.edercnj.wallettimeline.service.impl;


import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;
import com.br.edercnj.wallettimeline.repository.FinancialMovementRepository;
import com.br.edercnj.wallettimeline.service.FinancialMovementService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FinancialMovementServiceImpl implements FinancialMovementService {

    private final FinancialMovementRepository financialMovementRepository;

    public FinancialMovementServiceImpl(FinancialMovementRepository financialMovementRepository) {this.financialMovementRepository = financialMovementRepository;}

    @Override
    public void createFinancialMovement(FinancialMovement financialMovement) {
        financialMovementRepository.save(financialMovement);
    }

    @Override
    public Flux<FinancialMovement> findByUserId(String userId) {
        return financialMovementRepository.findByUserId(userId);
    }
}
