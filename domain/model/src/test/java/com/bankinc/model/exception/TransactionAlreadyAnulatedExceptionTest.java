package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionAlreadyAnulatedExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Transaction is already anulated.";
        TransactionAlreadyAnulatedException exception = new TransactionAlreadyAnulatedException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionThrow() {
        String errorMessage = "Transaction is already anulated.";

        RuntimeException thrown = assertThrows(TransactionAlreadyAnulatedException.class, () -> {
            throw new TransactionAlreadyAnulatedException(errorMessage);
        });

        assertThat(thrown.getMessage()).isEqualTo(errorMessage);
    }
}
