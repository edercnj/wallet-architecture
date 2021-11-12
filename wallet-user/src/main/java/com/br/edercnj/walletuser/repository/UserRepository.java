package com.br.edercnj.walletuser.repository;


import com.br.edercnj.walletuser.model.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByUsername(String username);
}
