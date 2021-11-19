package com.br.edercnj.walletuser.application.ports.outbound;

import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;

public interface UserRepositoryPort {

    User findByUsername(String username) throws UserNotFoundException;

    User findUserById(String userId) throws UserNotFoundException;

    User saveUser(User user);

}
