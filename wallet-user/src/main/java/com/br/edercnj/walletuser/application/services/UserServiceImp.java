package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.entites.User;
import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.ports.inbound.UserService;
import com.br.edercnj.walletuser.application.ports.outbound.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {this.userRepository = userRepository;}

    public BigDecimal depositInWallet(User user, BigDecimal valueToDeposit) {
        user.depositInWallet(valueToDeposit);
        User userUpdated = userRepository.updateWalletBalance(user);
        return userUpdated.getWallet().getBalance();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public BigDecimal withdrawInWallet(User user, BigDecimal valueToWithdraw) throws InsufficientFundsException {
        user.withdrawInWallet(valueToWithdraw);
        userRepository.updateWalletBalance(user);
        return user.getWallet().getBalance();
    }
}
