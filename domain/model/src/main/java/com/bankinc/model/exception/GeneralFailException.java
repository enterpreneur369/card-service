package com.bankinc.model.exception;

public class GeneralFailException extends RuntimeException {
    public GeneralFailException(String message) {
        super(message);
    }
}