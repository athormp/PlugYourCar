package com.plugyourcar.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 
@ControllerAdvice
public class ExceptionHandlingController {
 
    @ExceptionHandler(UserNameOrEmailExistsException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(UserNameOrEmailExistsException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("1001");
        response.setErrorMessage(ex.getMessage());
 
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ExceptionResponse> saldoInsuficienteError(SaldoInsuficienteException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("1002");
        response.setErrorMessage(ex.getMessage());
 
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(ConectorException.class)
    public ResponseEntity<ExceptionResponse> conectorError(ConectorException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("1004");
        response.setErrorMessage(ex.getMessage());
 
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }
    
}
