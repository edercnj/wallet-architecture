package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;

public interface FindExistentUser {
    public User findByUsername(String username) throws UserNotFoundException;
}
