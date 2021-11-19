package com.br.edercnj.walletuser.adapters.inbound.web.api.v1;

import com.br.edercnj.walletuser.application.domain.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import com.br.edercnj.walletuser.adapters.dto.ErrorResponseDto;
import com.br.edercnj.walletuser.adapters.dto.FinancialMovementDto;
import com.br.edercnj.walletuser.adapters.dto.WithdrawDto;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import com.br.edercnj.walletuser.application.domain.entities.Withdraw;
import com.br.edercnj.walletuser.application.services.WithdrawService;
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
public class WithdrawController {

    private final WithdrawService withdrawService;

    private final ModelMapper mapper;

    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
        this.mapper = new ModelMapper();
    }

    @ApiOperation(value = "Withdraw a certain amount in the user's wallet")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "Withdraw made successfully", response = FinancialMovementDto.class),
                    @ApiResponse(code = 400, message = "Invalid request parameters or insufficient funds", response = ErrorResponseDto.class),
                    @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseDto.class)
            })

    @PostMapping(value = "/wallets/withdraws", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FinancialMovementDto> deposit(@RequestBody @Validated WithdrawDto withdrawDto) throws UserNotFoundException, InsufficientFundsException {
        FinancialMovement financialMovement = withdrawService.withdraw(mapper.map(withdrawDto, Withdraw.class));
        FinancialMovementDto response = mapper.map(financialMovement, FinancialMovementDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
