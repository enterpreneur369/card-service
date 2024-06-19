package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardNotValidBalanceExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        String errorMessage = "Invalid balance amount: -100";
        CardNotValidBalanceException exception = new CardNotValidBalanceException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionType() {
        String errorMessage = "Invalid balance amount: -100";
        CardNotValidBalanceException exception = new CardNotValidBalanceException(errorMessage);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}
