package com.br.edercnj.walletuser.mapper;

import com.br.edercnj.walletuser.model.dto.UserDto;
import com.br.edercnj.walletuser.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);

    User dtoToUser(UserDto dto);

}
