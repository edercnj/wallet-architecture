package com.br.edercnj.walletuser.web.api.v1;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import com.br.edercnj.walletuser.exception.UserAlreadyRegisteredException;
import com.br.edercnj.walletuser.exception.UserNotFoundException;
import com.br.edercnj.walletuser.model.dto.ErrorResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ExceptionControllerAdviceTest {

    private ExceptionControllerAdvice controllerAdvice;

    @BeforeEach
    void setUp() {
        controllerAdvice = new ExceptionControllerAdvice();
    }

    @Test
    void genericException() {
        ResponseEntity<ErrorResponseDto> response = controllerAdvice.genericException(new Exception("teste"));
        Assertions.assertEquals("teste", response.getBody().getDeveloperMessage());
        Assertions.assertEquals("Unknown error", response.getBody().getUserMessage());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getBody().getHttpStatus());
    }

    @Test
    void userAlreadyRegisteredException() {
        ResponseEntity<ErrorResponseDto> response = controllerAdvice.userAlreadyRegisteredException(new UserAlreadyRegisteredException());
        Assertions.assertEquals("User already registered", response.getBody().getDeveloperMessage());
        Assertions.assertEquals("User already registered", response.getBody().getUserMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getHttpStatus());
    }

    @Test
    void userNotFoundException() {
        ResponseEntity<ErrorResponseDto> response = controllerAdvice.userNotFoundException(new UserNotFoundException());
        Assertions.assertEquals("User not found.", response.getBody().getDeveloperMessage());
        Assertions.assertEquals("User not found.", response.getBody().getUserMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getBody().getHttpStatus());

    }

    @Test
    void insufficientFundsException() {
        ResponseEntity<ErrorResponseDto> response = controllerAdvice.insufficientFundsException(new InsufficientFundsException());
        Assertions.assertEquals("Insufficient Funds", response.getBody().getDeveloperMessage());
        Assertions.assertEquals("Insufficient Funds", response.getBody().getUserMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getBody().getHttpStatus());
    }
}