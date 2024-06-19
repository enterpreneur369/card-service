package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardInactiveExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        String errorMessage = "Card must be inactive to perform this operation.";
        CardInactiveException exception = new CardInactiveException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionType() {
        String errorMessage = "Card must be inactive to perform this operation.";
        CardInactiveException exception = new CardInactiveException(errorMessage);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}
