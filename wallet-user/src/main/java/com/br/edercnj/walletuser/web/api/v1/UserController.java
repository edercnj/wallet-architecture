package com.br.edercnj.walletuser.web.api.v1;

import com.br.edercnj.walletuser.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.dto.ErrorResponseDto;
import com.br.edercnj.walletuser.model.dto.UserDto;
import com.br.edercnj.walletuser.model.entities.User;
import com.br.edercnj.walletuser.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;
    public UserController(UserService userService) {
        this.userService = userService;
        this.mapper = new ModelMapper();
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
        User user = userService.createUser(mapper.map(dto, User.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(user,UserDto.class));
    }

    @ApiOperation(value = "Create user with your wallet")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "User found successfully", response = UserDto.class),
                    @ApiResponse(code = 400, message = "Invalid request parameters", response = ErrorResponseDto.class),
                    @ApiResponse(code = 404, message = "User not find", response = ErrorResponseDto.class),
                    @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseDto.class)
            })
    @GetMapping(value = "/user/find", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDto> create(@RequestParam String username) throws  UserNotFoundException {
        User user = userService.findUserByUsername(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(user,UserDto.class));
    }
}
