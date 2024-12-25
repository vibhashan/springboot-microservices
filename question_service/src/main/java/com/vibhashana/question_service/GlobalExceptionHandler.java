package com.vibhashana.question_service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vibhashana.question_service.errors.QuestionException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<?> handle(QuestionException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
    }
}
