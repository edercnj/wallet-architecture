package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.model.dto.UserDto;
import com.br.edercnj.walletuser.model.dto.WalletDto;

public class UserDtoMock {

    public static UserDto createUserDto() {
        UserDto dto = new UserDto();
        dto.setUsername("teste");
        dto.setName("teste");
        dto.setWallet(new WalletDto());
        return dto;
    }
}
