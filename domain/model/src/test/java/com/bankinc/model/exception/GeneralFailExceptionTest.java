package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeneralFailExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "This is a general failure.";
        GeneralFailException exception = new GeneralFailException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionThrow() {
        String errorMessage = "This is a general failure.";

        RuntimeException thrown = assertThrows(GeneralFailException.class, () -> {
            throw new GeneralFailException(errorMessage);
        });

        assertThat(thrown.getMessage()).isEqualTo(errorMessage);
    }
}
