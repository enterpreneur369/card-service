package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsufficientErrorExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Insufficient balance to perform the operation.";
        InsufficientErrorException exception = new InsufficientErrorException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionThrow() {
        String errorMessage = "Insufficient balance to perform the operation.";

        RuntimeException thrown = assertThrows(InsufficientErrorException.class, () -> {
            throw new InsufficientErrorException(errorMessage);
        });

        assertThat(thrown.getMessage()).isEqualTo(errorMessage);
    }
}
