package com.br.edercnj.wallettimeline.service;


import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;
import reactor.core.publisher.Flux;

public interface FinancialMovementService {
    void createFinancialMovement(FinancialMovement financialMovement);

    Flux<FinancialMovement> findByUserId(String userId);
}
