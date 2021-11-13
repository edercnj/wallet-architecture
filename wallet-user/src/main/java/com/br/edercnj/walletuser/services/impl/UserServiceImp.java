package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.repository.UserRepository;
import com.br.edercnj.walletuser.services.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BigDecimal depositInWallet(User user, BigDecimal valueToDeposit) {
        user.depositInWallet(valueToDeposit);
        User savedEntity = userRepository.save(user);
        return savedEntity.getWallet().getBalance();
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        Optional<User> entity = userRepository.findByUsername(username);
        if (entity.isEmpty()) {
            throw new UserNotFoundException();
        }
        return entity.get();
    }

    public User findUserById(String id) throws UserNotFoundException {
        Optional<User> entity = userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new UserNotFoundException();
        }
        return entity.get();
    }

    public BigDecimal withdrawInWallet(User user, BigDecimal valueToWithdraw) throws InsufficientFundsException {
        user.withdrawInWallet(valueToWithdraw);
        User savedEntity = userRepository.save(user);
        return savedEntity.getWallet().getBalance();
    }

    @Override
    public User createUser(User user) throws UserAlreadyRegisteredException {
        Optional<User> userSearched = userRepository.findByUsername(user.getUsername());
        if (userSearched.isPresent()) {
            throw new UserAlreadyRegisteredException();
        }
        return userRepository.save(user);
    }
}
