package com.br.edercnj.walletuser.services;

import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.exception.InsufficientFundsException;

import java.math.BigDecimal;

public interface UserService {

    BigDecimal depositInWallet(User user, BigDecimal valueToDeposit);

    User findUserByUsername(String username);

    BigDecimal withdrawInWallet(User user, BigDecimal valueToWithdraw) throws InsufficientFundsException;
}
