package com.bankinc.api.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReloadBalanceRequestTest {

    @Test
    void reloadBalanceRequest_ShouldCreateRecordWithCorrectValues() {
        String expectedCardId = "123456789";
        String expectedBalance = "1000";

        ReloadBalanceRequest reloadBalanceRequest = new ReloadBalanceRequest(expectedCardId, expectedBalance);

        assertNotNull(reloadBalanceRequest);
        assertEquals(expectedCardId, reloadBalanceRequest.cardId());
        assertEquals(expectedBalance, reloadBalanceRequest.balance());
    }

    @Test
    void reloadBalanceRequest_ShouldHaveCorrectToString() {
        String cardId = "123456789";
        String balance = "1000";

        ReloadBalanceRequest reloadBalanceRequest = new ReloadBalanceRequest(cardId, balance);

        String expectedString = "ReloadBalanceRequest[cardId=123456789, balance=1000]";
        assertEquals(expectedString, reloadBalanceRequest.toString());
    }

    @Test
    void reloadBalanceRequest_ShouldHaveCorrectEqualsAndHashCode() {
        String cardId1 = "123456789";
        String cardId2 = "987654321";
        String balance1 = "1000";
        String balance2 = "2000";

        ReloadBalanceRequest reloadBalanceRequest1 = new ReloadBalanceRequest(cardId1, balance1);
        ReloadBalanceRequest reloadBalanceRequest2 = new ReloadBalanceRequest(cardId1, balance1);
        ReloadBalanceRequest reloadBalanceRequest3 = new ReloadBalanceRequest(cardId2, balance2);

        assertEquals(reloadBalanceRequest1, reloadBalanceRequest2);
        assertNotEquals(reloadBalanceRequest1, reloadBalanceRequest3);

        assertEquals(reloadBalanceRequest1.hashCode(), reloadBalanceRequest2.hashCode());
        assertNotEquals(reloadBalanceRequest1.hashCode(), reloadBalanceRequest3.hashCode());
    }
}
