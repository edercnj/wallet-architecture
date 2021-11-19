package com.br.edercnj.walletuser.adapters.outbound.persistence.mongo.entities;


import com.br.edercnj.walletuser.application.domain.entities.enums.FinancialMovementType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Document
@Data
public class FinancialMovementMongoEntity {
    @Id
    private String id;
    private UUID movementIdentifier;
    private Date dateOfFinancialMovement;
    private FinancialMovementType financialMovementType;
    private String userId;
    private BigDecimal amount;

    public FinancialMovementMongoEntity() {
    }

    public FinancialMovementMongoEntity(FinancialMovementType financialMovementType, String userId, BigDecimal amount) {
        movementIdentifier = UUID.randomUUID();
        this.dateOfFinancialMovement = new Date();
        this.financialMovementType = financialMovementType;
        this.userId = userId;
        this.amount = amount;
    }
}
