package com.bankinc.api.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AnnulationRequestTest {

    @Test
    void annulationRequest_ShouldCreateRecordWithCorrectValues() {
        String expectedCardId = "123456789";
        String expectedTransactionId = "987654321";

        AnnulationRequest annulationRequest = new AnnulationRequest(expectedCardId, expectedTransactionId);

        assertNotNull(annulationRequest);
        assertEquals(expectedCardId, annulationRequest.cardId());
        assertEquals(expectedTransactionId, annulationRequest.transactionId());
    }

    @Test
    void annulationRequest_ShouldHaveCorrectToString() {
        String cardId = "123456789";
        String transactionId = "987654321";

        AnnulationRequest annulationRequest = new AnnulationRequest(cardId, transactionId);

        String expectedString = "AnnulationRequest[cardId=123456789, transactionId=987654321]";
        assertEquals(expectedString, annulationRequest.toString());
    }

    @Test
    void annulationRequest_ShouldHaveCorrectEqualsAndHashCode() {
        String cardId1 = "123456789";
        String transactionId1 = "987654321";

        String cardId2 = "987654321";
        String transactionId2 = "123456789";

        AnnulationRequest annulationRequest1 = new AnnulationRequest(cardId1, transactionId1);
        AnnulationRequest annulationRequest2 = new AnnulationRequest(cardId1, transactionId1);
        AnnulationRequest annulationRequest3 = new AnnulationRequest(cardId2, transactionId2);

        assertEquals(annulationRequest1, annulationRequest2);
        assertNotEquals(annulationRequest1, annulationRequest3);

        assertEquals(annulationRequest1.hashCode(), annulationRequest2.hashCode());
        assertNotEquals(annulationRequest1.hashCode(), annulationRequest3.hashCode());
    }
}
