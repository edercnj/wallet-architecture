package com.br.edercnj.walletuser.services;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.entities.User;

import java.math.BigDecimal;

public interface UserService {

    BigDecimal depositInWallet(User user, BigDecimal valueToDeposit);

    User findUserByUsername(String username) throws UserNotFoundException;

    BigDecimal withdrawInWallet(User user, BigDecimal valueToWithdraw) throws InsufficientFundsException;
}
