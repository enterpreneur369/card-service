package com.bankinc.model.exception;

public class TransactionAnulationTimeExceededException extends RuntimeException {
    public TransactionAnulationTimeExceededException(String message) {
        super(message);
    }
}
