package com.br.edercnj.walletuser.application.ports;

import com.br.edercnj.walletuser.application.domain.entites.User;

public interface UserRepository {

    User createUser(User user);

    User findByUsername(String username);

    User updateWalletBalance(User user);

}
