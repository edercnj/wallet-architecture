package com.br.edercnj.walletuser.mapper;

import com.br.edercnj.walletuser.model.dto.FinancialMovementDto;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FinancialMovementMapper {

    FinancialMovementMapper INSTANCE = Mappers.getMapper(FinancialMovementMapper.class);

    FinancialMovementDto financialMovementToDto(FinancialMovement financialMovement);

    FinancialMovement dtoToFinancialMovement(FinancialMovementDto dto);
}
