package com.br.edercnj.walletuser.application.ports.inbound;

import com.br.edercnj.walletuser.application.domain.entites.User;
import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;

import java.math.BigDecimal;

public interface UserService {

    BigDecimal depositInWallet(User user, BigDecimal valueToDeposit);

    User findUserByUsername(String username);

    BigDecimal withdrawInWallet(User user, BigDecimal valueToWithdraw) throws InsufficientFundsException;
}
