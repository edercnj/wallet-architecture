package com.br.edercnj.walletuser.adapters.outbound.persistence.mongo;


import com.br.edercnj.walletuser.adapters.outbound.persistence.mongo.entities.UserMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSpringDataMongoRepository extends MongoRepository<UserMongoEntity, String> {

    Optional<UserMongoEntity> findByUsername(String username);
}
