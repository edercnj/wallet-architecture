package com.br.edercnj.walletuser.application.services.impl;

import com.br.edercnj.walletuser.application.ports.outbound.UserRepositoryPort;
import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.services.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepositoryPort repository;

    public UserServiceImpl(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public BigDecimal depositInWallet(User user, BigDecimal valueToDeposit) {
        user.depositInWallet(valueToDeposit);
        User savedEntity = repository.saveUser(user);
        return savedEntity.getWallet().getBalance();
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        return repository.findByUsername(username);
    }

    public User findUserById(String id) throws UserNotFoundException {
        return repository.findUserById(id);
    }

    public BigDecimal withdrawInWallet(User user, BigDecimal valueToWithdraw) throws InsufficientFundsException {
        user.withdrawInWallet(valueToWithdraw);
        User savedEntity = repository.saveUser(user);
        return savedEntity.getWallet().getBalance();
    }

    @Override
    public User createUser(User user) throws UserAlreadyRegisteredException {
        try {
            repository.findByUsername(user.getUsername());
            throw new UserAlreadyRegisteredException();
        } catch (UserNotFoundException e) {
            return repository.saveUser(user);
        }
    }
}
