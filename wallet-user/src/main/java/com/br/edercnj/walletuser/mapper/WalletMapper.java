package com.br.edercnj.walletuser.mapper;

import com.br.edercnj.walletuser.model.dto.WalletDto;
import com.br.edercnj.walletuser.model.entities.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    WalletDto walletToDto(Wallet user);

    Wallet dtoToWallet(WalletDto dto);
}
