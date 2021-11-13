package com.br.edercnj.walletuser.mapper;

import com.br.edercnj.walletuser.model.dto.DepositDto;
import com.br.edercnj.walletuser.model.entities.Deposit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepositMapper {
    DepositMapper INSTANCE = Mappers.getMapper(DepositMapper.class);

    DepositDto depositToDto(Deposit deposit);

    Deposit dtoToDeposit(DepositDto deposit);
}
