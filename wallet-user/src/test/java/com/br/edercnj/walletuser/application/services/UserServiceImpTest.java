package com.br.edercnj.walletuser.application.services;

import com.br.edercnj.walletuser.application.domain.entites.User;
import com.br.edercnj.walletuser.application.ports.inbound.UserService;
import com.br.edercnj.walletuser.application.ports.outbound.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImpTest {
    @Mock
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImp(userRepository);
        user = new User("edercnj", "teste", "Eder", new BigDecimal(500));
        when(userRepository.createUser(any(User.class))).thenReturn(mock(User.class));
        when(userRepository.findByUsername(anyString())).thenReturn(mock(User.class));
        when(userRepository.updateWalletBalance(any(User.class))).thenReturn(mock(User.class));
    }

    @Test
    void depositInWallet_should_be_call_userRepository_updateWalletBalance() {
        userService.depositInWallet(user, new BigDecimal(100));
        verify(userService, times(1)).depositInWallet(any(User.class), any(BigDecimal.class));
    }

    @Test
    void findUserByUsername() {
    }

    @Test
    void withdrawInWallet() {
    }
}