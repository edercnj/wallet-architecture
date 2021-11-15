package com.br.edercnj.wallettimeline.repository;


import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FinancialMovementRepository extends MongoRepository<FinancialMovement, String> {

    List<FinancialMovement> findByUserId(String userId);
}
