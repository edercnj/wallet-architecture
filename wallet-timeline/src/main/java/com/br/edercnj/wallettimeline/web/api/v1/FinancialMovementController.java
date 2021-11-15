package com.br.edercnj.wallettimeline.web.api.v1;

import com.br.edercnj.wallettimeline.model.dto.ErrorResponseDto;
import com.br.edercnj.wallettimeline.model.dto.FinancialMovementDto;
import com.br.edercnj.wallettimeline.model.entities.FinancialMovement;
import com.br.edercnj.wallettimeline.service.FinancialMovementService;
import com.br.edercnj.wallettimeline.service.impl.FinancialMovementServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value = "Returns a user's financial timeline")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Query success", response = FinancialMovementDto.class, responseContainer = "List"),
                    @ApiResponse(code = 404, message = "User not found", response = ErrorResponseDto.class),
                    @ApiResponse(code = 400, message = "Invalid request parameters", response = ErrorResponseDto.class),
                    @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseDto.class)
            })
    @GetMapping(value = "/timeline/financial-movements/user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FinancialMovementDto>> getFinancialMovementsFromUser(@PathVariable(value = "id") String userId) {
        List<FinancialMovement> financialMovement = financialMovementService.findByUserId(userId);
        List<FinancialMovementDto> financialMovements = mapper.map(financialMovement, new TypeToken<List<FinancialMovement>>() {}.getType());
        return ResponseEntity.status(HttpStatus.OK).body(financialMovements);
    }
}
