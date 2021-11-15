package com.br.edercnj.wallettimeline.repository;



import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface FinancialMovementRepository extends ReactiveMongoRepository<FinancialMovement, String> {

    Flux<FinancialMovement> findByUserId(String userId);
}
