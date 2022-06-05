package com.br.edercnj.credentials.application.persistence.mongo;

import com.br.edercnj.credentials.application.persistence.mongo.entities.AccountCredentialMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountCredentialSpringDataMongoRepository extends MongoRepository<AccountCredentialMongoEntity, String> {

    Optional<AccountCredentialMongoEntity> findByUsername(String username);
}
