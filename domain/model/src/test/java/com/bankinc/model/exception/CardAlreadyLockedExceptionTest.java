package com.bankinc.model.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardAlreadyLockedExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        String errorMessage = "Card is already locked.";
        CardAlreadyLockedException exception = new CardAlreadyLockedException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionType() {
        String errorMessage = "Card is already locked.";
        CardAlreadyLockedException exception = new CardAlreadyLockedException(errorMessage);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}