package com.br.edercnj.walletuser.application.services.impl;

import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.application.ports.outbound.UserRepositoryPort;
import com.br.edercnj.walletuser.application.services.FindExistentUser;
import org.springframework.stereotype.Service;

@Service
public class FindExistentUserImpl implements FindExistentUser {

    private final UserRepositoryPort repository;

    public FindExistentUserImpl(UserRepositoryPort repository) {this.repository = repository;}

    public User findByUsername(String username) throws UserNotFoundException {
        return repository.findByUsername(username);
    }
}
