package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.domain.exception.UserAlreadyRegisteredException;

public interface CreateNewUser {

    public User create(User user) throws UserAlreadyRegisteredException;
}
