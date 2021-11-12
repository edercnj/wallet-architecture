package com.br.edercnj.walletuser.adapters.outbound.persistence.entities;

import com.br.edercnj.walletuser.application.domain.entites.FinancialMovementType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Document(collation = "FinancialMovementEntity")
@Data
public class FinancialMovementEntity {
    @Id
    private int id;
    @Indexed(unique = true)
    private UUID movementIdentifier;
    private Date dateOfFinancialMovement;
    private FinancialMovementType financialMovementType;
    private UserEntity user;
    private BigDecimal amount;
}
