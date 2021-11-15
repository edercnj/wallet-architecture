package com.br.edercnj.wallettimeline.model.dto;


import com.br.edercnj.wallettimeline.model.entities.FinancialMovementType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FinancialMovementDto {
    @NotNull
    private UUID movementIdentifier;
    @NotNull
    private Date dateOfFinancialMovement;
    @NotNull
    private FinancialMovementType financialMovementType;
    @NotNull
    private String userId;
    @NotNull
    private BigDecimal amount;
}