package com.br.edercnj.billpayment.web.api.v1;

import com.br.edercnj.billpayment.exception.InsufficientFundsException;
import com.br.edercnj.billpayment.exception.UserNotFoundException;
import com.br.edercnj.billpayment.model.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdivice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> genericException(Exception e) {
        log.error(e.getMessage());
        ErrorResponseDto response = ErrorResponseDto
                .builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .userMessage("Unknown errorl")
                .developerMessage(e.getMessage())
                .build();

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }


    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> userNotFoundException(UserNotFoundException e) {
        log.error(e.getMessage());
        ErrorResponseDto response = ErrorResponseDto
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .userMessage(e.getMessage())
                .developerMessage(e.getMessage())
                .build();

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @ExceptionHandler({InsufficientFundsException.class})
    public ResponseEntity<ErrorResponseDto> insufficientFundsException(InsufficientFundsException e) {
        log.error(e.getMessage());
        ErrorResponseDto response = ErrorResponseDto
                .builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .userMessage(e.getMessage())
                .developerMessage(e.getMessage())
                .build();

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
