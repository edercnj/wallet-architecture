package com.br.edercnj.walletuser.model.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Document
@Data
public class FinancialMovement {
    @Id
    private int id;
    @Indexed(unique = true)
    private UUID movementIdentifier;
    private Date dateOfFinancialMovement;
    private FinancialMovementType financialMovementType;
    private User user;
    private BigDecimal amount;

    public FinancialMovement() {
    }

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
