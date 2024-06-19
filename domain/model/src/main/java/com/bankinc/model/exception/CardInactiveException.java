package com.bankinc.model.exception;

public class CardInactiveException extends RuntimeException {
    public CardInactiveException(String message) {
        super(message);
    }
}
