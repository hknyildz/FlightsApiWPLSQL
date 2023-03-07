package com.hknyildz.FlightsApi.Exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    private final String error;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiException(String error, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.error = error;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
