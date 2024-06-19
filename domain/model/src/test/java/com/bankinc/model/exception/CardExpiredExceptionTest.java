package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardExpiredExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        String errorMessage = "Card has expired.";
        CardExpiredException exception = new CardExpiredException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionType() {
        String errorMessage = "Card has expired.";
        CardExpiredException exception = new CardExpiredException(errorMessage);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}