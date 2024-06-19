package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardWithProductNorValidExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        String errorMessage = "Product ID must be a valid number.";
        CardWithProductNorValidException exception = new CardWithProductNorValidException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionType() {
        String errorMessage = "Product ID must be a valid number.";
        CardWithProductNorValidException exception = new CardWithProductNorValidException(errorMessage);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}
