package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardFailedUpdateExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        String errorMessage = "Failed to update card.";
        CardFailedUpdateException exception = new CardFailedUpdateException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionType() {
        String errorMessage = "Failed to update card.";
        CardFailedUpdateException exception = new CardFailedUpdateException(errorMessage);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}