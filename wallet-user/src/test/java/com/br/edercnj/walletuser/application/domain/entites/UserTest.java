package com.br.edercnj.walletuser.application.domain.entites;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("edercnj", "teste", "Eder", new BigDecimal(10));
    }

    @Test
    void when_call_constructor_without_balance_userWallet_sound_be_return_balance_0()
    {    user = new User("edercnj", "teste", "Eder");
        Assertions.assertEquals(new BigDecimal(0),user.getWallet().getBalance() );
    }

    @Test
    void when_balance_is_10_and_deposit_10_depositInWallet_should_be_return_20() {
        BigDecimal newBalance = user.depositInWallet(new BigDecimal(10));
        Assertions.assertEquals(new BigDecimal(20), newBalance);
    }

    @Test
    void when_balance_is_10_and_withdraw_10_withdrawInWallet_should_be_return_0() throws InsufficientFundsException {
        BigDecimal newBalance = user.withdrawInWallet(new BigDecimal(10));
        Assertions.assertEquals(new BigDecimal(0), newBalance);
    }

    @Test
    void when_balance_is_10_and_withdraw_1001_withdrawInWallet_should_be_return_0() {
        Assertions.assertThrows(InsufficientFundsException.class, () -> user.withdrawInWallet(new BigDecimal("10.01")));
    }


    @Test
    void when_userName_is_Eder_getUsername_should_be_Return_eder() {
        Assertions.assertEquals("edercnj", user.getUsername());
    }

    @Test
    void when_password_is_test3_getPassword_should_be_Return_teste() {
        Assertions.assertEquals("teste", user.getPassword());
    }


    @Test
    void when_name_is_eder_getName_should_be_Return_Eder() {
        Assertions.assertEquals("Eder", user.getName());
    }

    @Test
    void when_getWallet_should_be_Return_NotNull() {
        Assertions.assertNotNull(user.getWallet());
    }

}