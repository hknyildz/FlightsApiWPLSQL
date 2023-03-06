package com.hknyildz.FlightsApi.Exception;

public class EntityNotFoundException extends RuntimeException {


    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
