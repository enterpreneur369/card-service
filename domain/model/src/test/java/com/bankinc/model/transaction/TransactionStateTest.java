package com.bankinc.model.transaction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionStateTest {

    @Test
    void testEnumInitialization() {
        assertThat(TransactionState.ACTIVE).isNotNull();
        assertThat(TransactionState.CANCELLED).isNotNull();
    }

    @Test
    void testEnumComparison() {
        assertThat(TransactionState.ACTIVE).isEqualTo(TransactionState.ACTIVE);
        assertThat(TransactionState.ACTIVE).isNotEqualTo(TransactionState.CANCELLED);

        assertThat(TransactionState.CANCELLED).isEqualTo(TransactionState.CANCELLED);
        assertThat(TransactionState.CANCELLED).isNotEqualTo(TransactionState.ACTIVE);
    }
}