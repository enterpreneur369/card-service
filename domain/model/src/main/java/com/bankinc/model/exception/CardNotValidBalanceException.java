package com.bankinc.model.exception;

public class CardNotValidBalanceException extends RuntimeException {
    public CardNotValidBalanceException(String message) {
        super(message);
    }
}
