package com.br.edercnj.walletuser.application.domain.entities;

import com.br.edercnj.walletuser.application.domain.entities.enums.FinancialMovementType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


public class FinancialMovement {
    private String id;
    private UUID movementIdentifier;
    private Date dateOfFinancialMovement;
    private FinancialMovementType financialMovementType;
    private String userId;
    private BigDecimal amount;

    public FinancialMovement() {
    }

    public FinancialMovement(FinancialMovementType financialMovementType, String userId, BigDecimal amount) {
        movementIdentifier = UUID.randomUUID();
        this.dateOfFinancialMovement = new Date();
        this.financialMovementType = financialMovementType;
        this.userId = userId;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getMovementIdentifier() {
        return movementIdentifier;
    }

    public void setMovementIdentifier(UUID movementIdentifier) {
        this.movementIdentifier = movementIdentifier;
    }

    public Date getDateOfFinancialMovement() {
        return dateOfFinancialMovement;
    }

    public void setDateOfFinancialMovement(Date dateOfFinancialMovement) {
        this.dateOfFinancialMovement = dateOfFinancialMovement;
    }

    public FinancialMovementType getFinancialMovementType() {
        return financialMovementType;
    }

    public void setFinancialMovementType(FinancialMovementType financialMovementType) {
        this.financialMovementType = financialMovementType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
