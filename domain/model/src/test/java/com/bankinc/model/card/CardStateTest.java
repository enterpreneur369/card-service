package com.bankinc.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class CardStateTest {

    @Test
    void testEnumInitialization() {
        assertThat(CardState.ACTIVE).isNotNull();
        assertThat(CardState.INACTIVE).isNotNull();
        assertThat(CardState.LOCKED).isNotNull();
    }

    @Test
    void testEnumComparison() {
        assertThat(CardState.ACTIVE).isEqualTo(CardState.ACTIVE);
        assertThat(CardState.ACTIVE).isNotEqualTo(CardState.INACTIVE);
        assertThat(CardState.ACTIVE).isNotEqualTo(CardState.LOCKED);

        assertThat(CardState.INACTIVE).isEqualTo(CardState.INACTIVE);
        assertThat(CardState.INACTIVE).isNotEqualTo(CardState.ACTIVE);
        assertThat(CardState.INACTIVE).isNotEqualTo(CardState.LOCKED);

        assertThat(CardState.LOCKED).isEqualTo(CardState.LOCKED);
        assertThat(CardState.LOCKED).isNotEqualTo(CardState.ACTIVE);
        assertThat(CardState.LOCKED).isNotEqualTo(CardState.INACTIVE);
    }
}