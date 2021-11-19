package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.application.domain.entities.User;

import java.math.BigDecimal;

public interface UserService {

    BigDecimal depositInWallet(User user, BigDecimal valueToDeposit);

    User findUserByUsername(String username) throws UserNotFoundException;

    User findUserById(String id) throws UserNotFoundException;

    BigDecimal withdrawInWallet(User user, BigDecimal valueToWithdraw) throws InsufficientFundsException;

    public User createUser(User user) throws UserAlreadyRegisteredException, UserNotFoundException;
}
