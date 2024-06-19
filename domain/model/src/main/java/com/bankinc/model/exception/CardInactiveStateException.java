package com.bankinc.model.exception;

public class CardInactiveStateException extends RuntimeException {
    public CardInactiveStateException(String message) {
        super(message);
    }
}
