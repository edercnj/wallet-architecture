package com.br.edercnj.walletuser.repository;


import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FinancialMovementRepository extends MongoRepository<FinancialMovement, String> {
}
