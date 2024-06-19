package com.bankinc.model.exception;

public class TransactionAlreadyAnulatedException extends RuntimeException {
    public TransactionAlreadyAnulatedException(String message) {
        super(message);
    }
}
