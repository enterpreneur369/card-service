package com.bankinc.model.exception;

public class CardLockedException extends RuntimeException {
    public CardLockedException(String message) {
        super(message);
    }
}
