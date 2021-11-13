package com.br.edercnj.walletuser.web.api.v1;

import com.br.edercnj.walletuser.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.mapper.UserMapper;
import com.br.edercnj.walletuser.model.dto.ErrorResponseDto;
import com.br.edercnj.walletuser.model.dto.UserDto;
import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create user with your wallet")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "User Successfully created", response = UserDto.class),
                    @ApiResponse(code = 400, message = "Invalid request parameters", response = ErrorResponseDto.class),
                    @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseDto.class)
            })
    @PostMapping(value = "/user/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDto> create(@RequestBody @Validated UserDto dto) throws UserAlreadyRegisteredException {
        User user = userService.createUser(UserMapper.INSTANCE.dtoToUser(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.INSTANCE.userToUserDto(user));
    }
}
