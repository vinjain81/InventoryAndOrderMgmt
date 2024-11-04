package com.bgarage.autopartsmgmt.common.exception.handler;

import com.bgarage.autopartsmgmt.common.exception.appexceptions.BadRequest;
import com.bgarage.autopartsmgmt.common.exception.appexceptions.ResourceNotFound;
import jakarta.annotation.Nullable;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFound ex, WebRequest request)
    {
        return createResponseEntity(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request)
    {
        return createResponseEntity(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequest ex, WebRequest request)
    {
        return createResponseEntity(Map.of("message", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request)
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return createResponseEntity(errors, ex.getStatusCode());
    }

    private ResponseEntity<Object> createResponseEntity(@Nullable Object body, HttpStatusCode statusCode){

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(body, headers, statusCode);
    }
}
