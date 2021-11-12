package com.br.edercnj.walletuser.model.entities;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class WalletTest {

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new Wallet(new BigDecimal(5));
    }

    @Test
    void when_deposit_5_and_initial_balance_is_5_then_return_10() {
        Assertions.assertEquals(new BigDecimal(10), wallet.deposit(new BigDecimal(5)));
    }

    @Test
    void when_withdraw_3_and_initial_balance_is_5_then_return_2() throws InsufficientFundsException {
        Assertions.assertEquals(new BigDecimal(3), wallet.withdraw(new BigDecimal(2)));
    }

    @Test
    void when_withdraw_7_and_initial_balance_is_5_then_throw_InsufficientFundsException() {
        Assertions.assertThrows(InsufficientFundsException.class, () -> wallet.withdraw(new BigDecimal(7)));
    }

    @Test
    void when_initial_balance_is_5_getBalance_should_be_return_5() {
        Assertions.assertEquals(new BigDecimal(5), wallet.getBalance());
    }

    @Test
    void when_create_new_wallet_with_default_constructor_should_be_return_default_value() {
        Wallet wallet = new Wallet();
        Assertions.assertEquals(new BigDecimal(0), wallet.getBalance());
    }
}