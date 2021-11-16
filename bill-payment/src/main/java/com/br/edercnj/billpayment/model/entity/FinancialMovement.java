package com.br.edercnj.billpayment.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

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
