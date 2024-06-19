package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardNotFoundExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        String errorMessage = "Card not found for number: 1234567890123456";
        CardNotFoundException exception = new CardNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionType() {
        String errorMessage = "Card not found for number: 1234567890123456";
        CardNotFoundException exception = new CardNotFoundException(errorMessage);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}
