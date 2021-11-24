package com.br.edercnj.walletuser.adapters.inbound.web.api.v1;

import com.br.edercnj.walletuser.adapters.dto.ErrorResponseDto;
import com.br.edercnj.walletuser.adapters.dto.UserDto;
import com.br.edercnj.walletuser.adapters.outbound.persistence.mongo.UserMongoRepository;
import com.br.edercnj.walletuser.adapters.outbound.persistence.mongo.UserSpringDataMongoRepository;
import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.domain.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.application.services.CreateNewUser;
import com.br.edercnj.walletuser.application.services.impl.CreateNewUserImpl;
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
public class CreateUserController {

    private final CreateNewUser createUser;
    private final ModelMapper mapper;

    public CreateUserController(UserSpringDataMongoRepository userSpringDataMongoRepository) {
        this.createUser = new CreateNewUserImpl(new UserMongoRepository(userSpringDataMongoRepository));
        this.mapper = new ModelMapper();
    }

    @ApiOperation(value = "Create user with your wallet")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "User Successfully created", response = UserDto.class),
                    @ApiResponse(code = 400, message = "Invalid request parameters", response = ErrorResponseDto.class),
                    @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseDto.class)
            })
    @PostMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> create(@RequestBody @Validated UserDto dto) throws UserAlreadyRegisteredException {
        User user = createUser.create(mapper.map(dto, User.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(user, UserDto.class));
    }
}
