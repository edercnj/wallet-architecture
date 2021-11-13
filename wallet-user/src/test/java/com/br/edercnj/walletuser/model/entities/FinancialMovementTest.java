package com.br.edercnj.walletuser.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

class FinancialMovementTest {

    private FinancialMovement financialMovement;

    @BeforeEach
    void setUp() {
        User user = new User("fulano@gmail.com", "Fulano de Tal", new BigDecimal(10000));
        financialMovement = new FinancialMovement(FinancialMovementType.DEPOSIT, user, new BigDecimal(500));
    }

    @Test
    void getMovementIdentifier_should_be_return_not_null() {
        UUID uuid = financialMovement.getMovementIdentifier();
        Assertions.assertNotNull(uuid);
    }

    @Test
    void getDateOfFinancialMovement_should_be_return_not_null() {
        Assertions.assertNotNull(financialMovement.getDateOfFinancialMovement());
    }

    @Test
    void getFinancialMovementType_should_be_return_deposit() {
        Assertions.assertEquals(FinancialMovementType.DEPOSIT, financialMovement.getFinancialMovementType());
    }

    @Test
    void getUser_should_be_return_user_whit_username_falano() {
        Assertions.assertEquals("fulano@gmail.com", financialMovement.getUser().getUsername());
    }
    @Test
    void getAmount() {
        Assertions.assertEquals(new BigDecimal(500), financialMovement.getAmount());
    }
}