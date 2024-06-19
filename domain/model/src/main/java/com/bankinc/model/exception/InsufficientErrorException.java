package com.bankinc.model.exception;

public class InsufficientErrorException  extends RuntimeException {
    public InsufficientErrorException(String message) {
        super(message);
    }
}
