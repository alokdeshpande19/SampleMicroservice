package com.centime.nameextractorservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler
    ResponseEntity<String> exceptionHandler(IllegalArgumentException e){
        return  ResponseEntity.badRequest().body(e.getMessage());
    }
}
