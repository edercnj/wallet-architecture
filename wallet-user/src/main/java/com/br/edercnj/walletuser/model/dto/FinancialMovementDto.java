package com.br.edercnj.walletuser.model.dto;

import com.br.edercnj.walletuser.model.entities.FinancialMovementType;
import com.br.edercnj.walletuser.model.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FinancialMovementDto {
    private UUID movementIdentifier;
    private Date dateOfFinancialMovement;
    private FinancialMovementType financialMovementType;
    private User user;
    private BigDecimal amount;
}