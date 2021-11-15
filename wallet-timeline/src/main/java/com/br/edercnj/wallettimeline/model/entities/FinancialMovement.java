package com.br.edercnj.wallettimeline.model.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Document
@Data
public class FinancialMovement {
    @Id
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
}
