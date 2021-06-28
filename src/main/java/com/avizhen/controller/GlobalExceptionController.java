package com.avizhen.controller;


import com.avizhen.dto.ExceptionResponseDto;
import com.avizhen.exception.ResourceAlreadyExistException;
import com.avizhen.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(value = {ResourceNotFoundException.class, NullPointerException.class})
    public ResponseEntity<ExceptionResponseDto> resourceNotFound(RuntimeException ex) {
        ExceptionResponseDto response = buildExceptionResponse(ex, "NOT_FOUND");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourceAlreadyExistException.class})
    public ResponseEntity<ExceptionResponseDto> resourceAlreadyExists(RuntimeException ex) {
        ExceptionResponseDto response = buildExceptionResponse(ex, "CONFLICT");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ExceptionResponseDto buildExceptionResponse(Exception ex, String errorCode) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setErrorCode(errorCode);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(new Date());
        return response;
    }
}
