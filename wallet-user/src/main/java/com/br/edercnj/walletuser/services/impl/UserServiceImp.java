package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.repository.UserRepository;
import com.br.edercnj.walletuser.services.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImp implements UserService {


    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BigDecimal depositInWallet(User user, BigDecimal valueToDeposit) {
        user.depositInWallet(valueToDeposit);
        User userUpdated = userRepository.save(user);
        return userUpdated.getWallet().getBalance();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public BigDecimal withdrawInWallet(User user, BigDecimal valueToWithdraw) throws InsufficientFundsException {
        user.withdrawInWallet(valueToWithdraw);
        User userUpdated = userRepository.save(user);
        return userUpdated.getWallet().getBalance();
    }
}
