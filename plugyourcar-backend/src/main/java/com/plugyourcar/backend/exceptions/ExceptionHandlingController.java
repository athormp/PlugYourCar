package com.plugyourcar.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    
}
