package com.br.edercnj.walletuser.services;

import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.repository.UserRepository;
import com.br.edercnj.walletuser.services.impl.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;

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
        when(userRepository.findByUsername(anyString())).thenReturn(user);
    }

    @Test
    void depositInWallet_should_be_call_userRepository_updateWalletBalance() {
        userService.depositInWallet(user, new BigDecimal(100));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findUserByUsername() {
    }

    @Test
    void withdrawInWallet() {
    }
}