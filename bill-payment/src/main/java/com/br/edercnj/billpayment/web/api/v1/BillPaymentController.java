package com.br.edercnj.billpayment.web.api.v1;

import com.br.edercnj.billpayment.model.dto.BillPaymentDto;
import com.br.edercnj.billpayment.model.dto.BillPaymentOrderDto;
import com.br.edercnj.billpayment.model.dto.ErrorResponseDto;
import com.br.edercnj.billpayment.model.entity.BillPayment;
import com.br.edercnj.billpayment.model.entity.BillPaymentOrder;
import com.br.edercnj.billpayment.service.BillPaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
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
public class BillPaymentController {

    private final BillPaymentService billPaymentService;
    private final ModelMapper modelMapper;

    public BillPaymentController(BillPaymentService billPaymentService) {
        this.billPaymentService = billPaymentService;
        this.modelMapper = new ModelMapper();
    }

    @ApiOperation(value = "Bill payment made successfully")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "Successfully created invoice payment request", response = BillPaymentOrderDto.class),
                    @ApiResponse(code = 400, message = "Invalid request parameters", response = ErrorResponseDto.class),
                    @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseDto.class)
            })
    @PostMapping(value = "/bill_payments", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BillPaymentOrderDto> deposit(@RequestBody @Validated BillPaymentDto  billPaymentDto)  {
        BillPaymentOrder billPaymentOrder = billPaymentService.pay(modelMapper.map(billPaymentDto, BillPayment.class));
        BillPaymentOrderDto response = modelMapper.map(billPaymentOrder, BillPaymentOrderDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
