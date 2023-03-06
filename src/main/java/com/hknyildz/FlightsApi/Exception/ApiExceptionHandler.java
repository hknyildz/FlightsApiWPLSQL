package com.hknyildz.FlightsApi.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {

        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        LOGGER.error("ApiRequestException(BAD_REQUEST): " + e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {

        ApiException apiException = new ApiException("Record Not Found: " + e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z")));
        LOGGER.error("EntityNotFoundException(RECORD_NOT_FOUND): " + e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ApiException apiException = new ApiException("Validation error: " + e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        LOGGER.error("EntityNotFoundException(RECORD_NOT_FOUND): " + e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DateTimeParseException.class})
    public ResponseEntity<Object> MethodArgumentNotValidException(DateTimeParseException e) {
        ApiException apiException = new ApiException("Time pattern(yyyy-mm-dd hh:mm) is not matching with: " + e.getParsedString(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        LOGGER.error("DateTimeParseException(Time pattern is not valid): " + e.getParsedString());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
