package com.br.edercnj.walletuser.web.api.v1;

import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.mapper.DepositMapper;
import com.br.edercnj.walletuser.mapper.FinancialMovementMapper;
import com.br.edercnj.walletuser.model.dto.DepositDto;
import com.br.edercnj.walletuser.model.dto.FinancialMovementDto;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import com.br.edercnj.walletuser.services.DepositService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/user/wallet/deposit")
    public ResponseEntity<FinancialMovementDto> deposit(@Validated DepositDto depositDto) throws UserNotFoundException {
        FinancialMovement financialMovement = depositService.deposit(DepositMapper.INSTANCE.dtoToDeposit(depositDto));
        FinancialMovementDto response = FinancialMovementMapper.INSTANCE.financialMovementToDto(financialMovement);
        return ResponseEntity.ok(response);
    }
}
