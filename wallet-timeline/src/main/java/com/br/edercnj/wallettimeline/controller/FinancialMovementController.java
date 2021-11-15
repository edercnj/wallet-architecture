package com.br.edercnj.wallettimeline.controller;

import com.br.edercnj.wallettimeline.model.dto.ErrorResponseDto;
import com.br.edercnj.wallettimeline.model.dto.FinancialMovementDto;
import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;
import com.br.edercnj.wallettimeline.service.FinancialMovementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FinancialMovementController {

    private final FinancialMovementService financialMovementService;
    private final ModelMapper mapper;

    public FinancialMovementController(FinancialMovementService financialMovementService) {
        this.financialMovementService = financialMovementService;
        this.mapper = new ModelMapper();
    }

    @ApiOperation(value = "Return all Financial Movement")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Query success", response = FinancialMovementDto.class, responseContainer = "List"),
                    @ApiResponse(code = 400, message = "Invalid request parameters", response = ErrorResponseDto.class),
                    @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseDto.class)
            })
    @GetMapping(value = "/timeline/financial-movements", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FinancialMovementDto>> getAllFinancialMovements() {
        List<FinancialMovement> financialMovement = financialMovementService.findAll();
        List<FinancialMovementDto> financialMovements = mapper.map(financialMovement, new TypeToken<List<FinancialMovement>>() {}.getType());
        return ResponseEntity.status(HttpStatus.OK).body(financialMovements);
    }
}
