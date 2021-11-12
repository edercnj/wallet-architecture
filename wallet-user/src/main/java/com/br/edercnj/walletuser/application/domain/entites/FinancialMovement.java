package com.br.edercnj.walletuser.application.domain.entites;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class FinancialMovement {
    private final UUID movementIdentifier;
    private final Date dateOfFinancialMovement;
    private final FinancialMovementType financialMovementType;
    private final User user;
    private final BigDecimal amount;

    public FinancialMovement(FinancialMovementType financialMovementType, User user, BigDecimal amount) {
        movementIdentifier = UUID.randomUUID();
        this.dateOfFinancialMovement = new Date();
        this.financialMovementType = financialMovementType;
        this.user = user;
        this.amount = amount;
    }

    public UUID getMovementIdentifier() {return movementIdentifier;}

    public Date getDateOfFinancialMovement() {return dateOfFinancialMovement;}

    public FinancialMovementType getFinancialMovementType() {return financialMovementType;}

    public User getUser() {return user;}

    public BigDecimal getAmount() {return amount;}
}
