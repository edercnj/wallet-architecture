package com.br.edercnj.walletuser.application.services.impl;

import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.domain.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.application.services.CreateNewUser;
import com.br.edercnj.walletuser.application.ports.outbound.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateNewUserImpl implements CreateNewUser {
    private final UserRepositoryPort repository;

    public CreateNewUserImpl(UserRepositoryPort repository) {this.repository = repository;}

    @Override
    public User create(User user) throws UserAlreadyRegisteredException {
        try {
            repository.findByUsername(user.getUsername());
            throw new UserAlreadyRegisteredException();
        } catch (UserNotFoundException e) {
            return repository.saveUser(user);
        }
    }
}
