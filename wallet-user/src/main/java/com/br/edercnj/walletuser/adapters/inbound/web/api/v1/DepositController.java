package com.br.edercnj.walletuser.adapters.inbound.web.api.v1;

import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.adapters.dto.DepositDto;
import com.br.edercnj.walletuser.adapters.dto.ErrorResponseDto;
import com.br.edercnj.walletuser.adapters.dto.FinancialMovementDto;
import com.br.edercnj.walletuser.application.domain.entities.Deposit;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.services.DepositService;
import com.br.edercnj.walletuser.application.services.FindExistentUser;
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
public class DepositController {

    private final DepositService depositService;
    private final FindExistentUser findExistentUser;

    private final ModelMapper modelMapper;

    public DepositController(DepositService depositService, FindExistentUser findExistentUser) {
        this.depositService = depositService;
        this.findExistentUser = findExistentUser;
        this.modelMapper = new ModelMapper();
    }

    @ApiOperation(value = "deposit a certain amount in the user's wallet")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "Deposit made successfully", response = FinancialMovementDto.class),
                    @ApiResponse(code = 400, message = "Invalid request parameters", response = ErrorResponseDto.class),
                    @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseDto.class)
            })
    @PostMapping(value = "wallets/deposits", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FinancialMovementDto> deposit(@RequestBody @Validated DepositDto depositDto) throws UserNotFoundException {
        findExistentUser.findByUsername(depositDto.getUsername());
        FinancialMovement financialMovement = depositService.deposit(modelMapper.map(depositDto, Deposit.class));
        FinancialMovementDto response = modelMapper.map(financialMovement, FinancialMovementDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
