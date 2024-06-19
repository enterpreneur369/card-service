package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionAnulationTimeExceededExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Transaction cannot be anulated after 24 hours.";
        TransactionAnulationTimeExceededException exception = new TransactionAnulationTimeExceededException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionThrow() {
        String errorMessage = "Transaction cannot be anulated after 24 hours.";

        RuntimeException thrown = assertThrows(TransactionAnulationTimeExceededException.class, () -> {
            throw new TransactionAnulationTimeExceededException(errorMessage);
        });

        assertThat(thrown.getMessage()).isEqualTo(errorMessage);
    }
}
