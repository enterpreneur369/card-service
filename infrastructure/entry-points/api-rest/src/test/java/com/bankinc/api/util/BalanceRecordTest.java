package com.bankinc.api.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class BalanceRecordTest {

    @Test
    void balanceRecord_ShouldCreateRecordWithCorrectBalance() {
        Long expectedBalance = 1000L;
        BalanceRecord balanceRecord = new BalanceRecord(expectedBalance);

        assertNotNull(balanceRecord);
        assertEquals(expectedBalance, balanceRecord.balance());
    }

    @Test
    void balanceRecord_ShouldHaveCorrectToString() {
        Long balance = 1000L;
        BalanceRecord balanceRecord = new BalanceRecord(balance);

        String expectedString = "BalanceRecord[balance=1000]";
        assertEquals(expectedString, balanceRecord.toString());
    }

    @Test
    void balanceRecord_ShouldHaveCorrectEqualsAndHashCode() {
        Long balance1 = 1000L;
        Long balance2 = 2000L;

        BalanceRecord balanceRecord1 = new BalanceRecord(balance1);
        BalanceRecord balanceRecord2 = new BalanceRecord(balance1);
        BalanceRecord balanceRecord3 = new BalanceRecord(balance2);

        assertEquals(balanceRecord1, balanceRecord2);
        assertNotEquals(balanceRecord1, balanceRecord3);

        assertEquals(balanceRecord1.hashCode(), balanceRecord2.hashCode());
        assertNotEquals(balanceRecord1.hashCode(), balanceRecord3.hashCode());
    }
}