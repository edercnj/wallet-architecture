package com.br.edercnj.walletuser.application.domain.services;

import com.br.edercnj.walletuser.application.domain.entites.User;
import com.br.edercnj.walletuser.application.ports.UserRepository;

import java.math.BigDecimal;

public class UserDepositInWalletService {

    private final UserRepository userRepository;

    public UserDepositInWalletService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private BigDecimal depositInWallet(User user, BigDecimal valueToDeposit) {
        user.depositInWallet(valueToDeposit);
        userRepository.updateWalletBalance(user);
        return user.getWallet().getBalance();
    }
}
