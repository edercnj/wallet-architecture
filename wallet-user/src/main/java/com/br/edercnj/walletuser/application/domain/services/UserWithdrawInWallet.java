package com.br.edercnj.walletuser.application.domain.services;

import com.br.edercnj.walletuser.application.domain.entites.User;
import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.ports.UserRepository;

import java.math.BigDecimal;

public class UserWithdrawInWallet {

    private final UserRepository userRepository;

    public UserWithdrawInWallet(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BigDecimal withdrawInWallet(User user, BigDecimal valueToWithdraw) throws InsufficientFundsException {
        user.withdrawInWallet(valueToWithdraw);
        userRepository.updateWalletBalance(user);
        return user.getWallet().getBalance();
    }
}
