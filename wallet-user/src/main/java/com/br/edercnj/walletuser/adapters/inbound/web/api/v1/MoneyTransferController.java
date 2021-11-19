package com.br.edercnj.walletuser.adapters.inbound.web.api.v1;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.adapters.dto.ErrorResponseDto;
import com.br.edercnj.walletuser.adapters.dto.FinancialMovementDto;
import com.br.edercnj.walletuser.adapters.dto.MoneyTransferDto;
import com.br.edercnj.walletuser.adapters.dto.UserDto;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.domain.entities.MoneyTransfer;
import com.br.edercnj.walletuser.application.services.MoneyTransferService;
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
public class MoneyTransferController {

    MoneyTransferService moneyTransferService;
    ModelMapper mapper;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
        this.mapper = new ModelMapper();
    }

    @ApiOperation(value = "Transfer money between users.")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "Transfer Successfully executed", response = UserDto.class),
                    @ApiResponse(code = 400, message = "Invalid request parameters", response = ErrorResponseDto.class),
                    @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseDto.class)
            })
    @PostMapping(value = "/wallets/money_transfers", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FinancialMovementDto> moneyTransfer(@RequestBody @Validated MoneyTransferDto dto) throws UserNotFoundException, InsufficientFundsException {
        FinancialMovement financialMovement = moneyTransferService.moneyTransfer(mapper.map(dto, MoneyTransfer.class));
        FinancialMovementDto response = mapper.map(financialMovement, FinancialMovementDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
