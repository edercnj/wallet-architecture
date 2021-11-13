package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class UserServiceImpTest {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserServiceImp userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("edercnj", "Eder", new BigDecimal(500));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
    }

    @Test
    void depositInWallet_should_be_call_userRepository_save() {
        userService.depositInWallet(user, new BigDecimal(100));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findUserByUsername_should_be_call_userRepository_findByUsername() throws UserNotFoundException {
        userService.findUserByUsername(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void findUserByUsername_should_be_throw_userNotFoundException() throws UserNotFoundException {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.findUserByUsername("teste"));
    }

    @Test
    void findUserById_should_be_call_userRepository_findByUsername() throws UserNotFoundException {
        userService.findUserById(anyString());
        verify(userRepository, times(1)).findById(anyString());
    }

    @Test
    void findUserById_should_be_throw_userNotFoundException() throws UserNotFoundException {
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.findUserById("teste"));
    }

    @Test
    void withdrawInWallet_should_be_call_userRepository_save() throws InsufficientFundsException {
        userService.withdrawInWallet(user, new BigDecimal(100));
        verify(userRepository, times(1)).save(any(User.class));
    }
}