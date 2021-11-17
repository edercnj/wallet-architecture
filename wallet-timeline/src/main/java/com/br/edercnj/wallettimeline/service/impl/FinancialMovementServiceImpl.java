package com.br.edercnj.wallettimeline.service.impl;


import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;
import com.br.edercnj.wallettimeline.repository.FinancialMovementRepository;
import com.br.edercnj.wallettimeline.service.FinancialMovementService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "financialMovementCache")
public class FinancialMovementServiceImpl implements FinancialMovementService {

    private final FinancialMovementRepository financialMovementRepository;

    public FinancialMovementServiceImpl(FinancialMovementRepository financialMovementRepository) {this.financialMovementRepository = financialMovementRepository;}

    @Override
    @CacheEvict(cacheNames = "financialMovement", allEntries = true)
    public void createFinancialMovement(FinancialMovement financialMovement) {
        financialMovementRepository.save(financialMovement);
    }

    @Override
    @Cacheable(cacheNames = "financialMovement", key="#root.method.name")
    public List<FinancialMovement> findByUserId(String userId) {
        return financialMovementRepository.findByUserId(userId);
    }
}
