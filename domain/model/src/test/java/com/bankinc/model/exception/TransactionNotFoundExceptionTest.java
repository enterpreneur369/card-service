package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Transaction not found for ID: 123456";
        TransactionNotFoundException exception = new TransactionNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionThrow() {
        String errorMessage = "Transaction not found for ID: 123456";

        RuntimeException thrown = assertThrows(TransactionNotFoundException.class, () -> {
            throw new TransactionNotFoundException(errorMessage);
        });

        assertThat(thrown.getMessage()).isEqualTo(errorMessage);
    }
}
