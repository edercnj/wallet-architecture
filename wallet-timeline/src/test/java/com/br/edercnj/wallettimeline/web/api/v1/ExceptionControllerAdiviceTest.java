package com.br.edercnj.wallettimeline.web.api.v1;

import com.br.edercnj.wallettimeline.exception.UserNotFoundException;
import com.br.edercnj.wallettimeline.model.dto.ErrorResponseDto;
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
    void userNotFoundException() {
        ResponseEntity<ErrorResponseDto> response = controllerAdvice.userNotFoundException(new UserNotFoundException());
        Assertions.assertEquals("User not found.", response.getBody().getDeveloperMessage());
        Assertions.assertEquals("User not found.", response.getBody().getUserMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getBody().getHttpStatus());

    }
}