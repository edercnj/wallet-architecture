package com.br.edercnj.walletuser.adapters.outbound.persistence;

import com.br.edercnj.walletuser.adapters.outbound.persistence.entities.UserEntity;
import com.br.edercnj.walletuser.application.domain.entites.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataMongoDbUserRepository extends MongoRepository<UserEntity, String> {

    public UserEntity findByUsername(String username);
}
