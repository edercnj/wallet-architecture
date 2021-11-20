package com.br.edercnj.walletuser.services.impl;

import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.ports.outbound.UserRepositoryPort;
import com.br.edercnj.walletuser.application.services.impl.UserServiceImpl;
import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.mocks.UserMock;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {
    @MockBean
    private UserRepositoryPort userSpringDataMongoRepository;
    @Autowired
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() throws UserNotFoundException {
        user = new User("edercnj", "Eder", new BigDecimal(500));
        when(userSpringDataMongoRepository.saveUser(any(User.class))).thenReturn(user);
        when(userSpringDataMongoRepository.findByUsername(anyString())).thenReturn(user);
        when(userSpringDataMongoRepository.findUserById(anyString())).thenReturn(user);
    }

    @Test
    void depositInWallet_should_be_call_userRepository_save() {
        userService.depositInWallet(user, new BigDecimal(100));
        verify(userSpringDataMongoRepository, times(1)).saveUser(any(User.class));
    }

    @Test
    void findUserByUsername_should_be_call_userRepository_findByUsername() throws UserNotFoundException {
        userService.findUserByUsername(anyString());
        verify(userSpringDataMongoRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void findUserByUsername_should_be_throw_userNotFoundException() throws UserNotFoundException {
        when(userSpringDataMongoRepository.findByUsername(anyString())).thenThrow(new UserNotFoundException());
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findUserByUsername("teste"));
    }

    @Test
    void findUserById_should_be_call_userRepository_findByUsername() throws UserNotFoundException {
        User user = userService.findUserById(anyString());
        verify(userSpringDataMongoRepository, times(1)).findUserById(anyString());
    }

    @Test
    void findUserById_should_be_throw_userNotFoundException() throws UserNotFoundException {
        when(userSpringDataMongoRepository.findUserById(anyString())).thenThrow(new UserNotFoundException());
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findUserById("teste"));
    }

    @Test
    void withdrawInWallet_should_be_call_userRepository_save() throws InsufficientFundsException {
        userService.withdrawInWallet(user, new BigDecimal(100));
        verify(userSpringDataMongoRepository, times(1)).saveUser(any(User.class));
    }

    @Test
    void createUser_should_be_call_userRepository_save() throws UserAlreadyRegisteredException, UserNotFoundException {
        when(userSpringDataMongoRepository.findByUsername(anyString())).thenThrow(new UserNotFoundException());
        userService.createUser(user);
        verify(userSpringDataMongoRepository, times(1)).saveUser(any(User.class));
    }

    @Test
    void createUser_should_be_call_userRepository_should_be_throw() throws UserNotFoundException {
        when(userSpringDataMongoRepository.findByUsername(anyString())).thenReturn(UserMock.createUser());
        Assertions.assertThrows(UserAlreadyRegisteredException.class, () -> userService.createUser(user));

    }
}