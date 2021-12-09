package com.lchalela.people.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private Map<String,Object> response = new HashMap<>();

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException nfe){
        response.clear();
        response.put("error",nfe.getMessage());
        response.put("time",LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        response.clear();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField().concat(" ").concat(err.getDefaultMessage()))
                .collect(Collectors.toList());
        response.put("time", LocalDateTime.now());
        response.put("errors",errors);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
