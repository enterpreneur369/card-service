package com.bankinc.model.exception;

public class CardAlreadyLockedException extends RuntimeException {
    public CardAlreadyLockedException(String message) {
        super(message);
    }
}
