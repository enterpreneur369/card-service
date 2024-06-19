package com.bankinc.api.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PurchaseRequestTest {

    @Test
    void purchaseRequest_ShouldCreateRecordWithCorrectValues() {
        String expectedCardId = "123456789";
        Long expectedPrice = 5000L;

        PurchaseRequest purchaseRequest = new PurchaseRequest(expectedCardId, expectedPrice);

        assertNotNull(purchaseRequest);
        assertEquals(expectedCardId, purchaseRequest.cardId());
        assertEquals(expectedPrice, purchaseRequest.price());
    }

    @Test
    void purchaseRequest_ShouldHaveCorrectToString() {
        String cardId = "123456789";
        Long price = 5000L;

        PurchaseRequest purchaseRequest = new PurchaseRequest(cardId, price);

        String expectedString = "PurchaseRequest[cardId=123456789, price=5000]";
        assertEquals(expectedString, purchaseRequest.toString());
    }

    @Test
    void purchaseRequest_ShouldHaveCorrectEqualsAndHashCode() {
        String cardId1 = "123456789";
        Long price1 = 5000L;

        String cardId2 = "987654321";
        Long price2 = 10000L;

        PurchaseRequest purchaseRequest1 = new PurchaseRequest(cardId1, price1);
        PurchaseRequest purchaseRequest2 = new PurchaseRequest(cardId1, price1);
        PurchaseRequest purchaseRequest3 = new PurchaseRequest(cardId2, price2);

        assertEquals(purchaseRequest1, purchaseRequest2);
        assertNotEquals(purchaseRequest1, purchaseRequest3);

        assertEquals(purchaseRequest1.hashCode(), purchaseRequest2.hashCode());
        assertNotEquals(purchaseRequest1.hashCode(), purchaseRequest3.hashCode());
    }
}
