package com.bankinc.api.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EnrollCardRequestTest {

    @Test
    void enrollCardRequest_ShouldCreateRecordWithCorrectValues() {
        String expectedCardId = "123456789";

        EnrollCardRequest enrollCardRequest = new EnrollCardRequest(expectedCardId);

        assertNotNull(enrollCardRequest);
        assertEquals(expectedCardId, enrollCardRequest.cardId());
    }

    @Test
    void enrollCardRequest_ShouldHaveCorrectToString() {
        String cardId = "123456789";

        EnrollCardRequest enrollCardRequest = new EnrollCardRequest(cardId);

        String expectedString = "EnrollCardRequest[cardId=123456789]";
        assertEquals(expectedString, enrollCardRequest.toString());
    }

    @Test
    void enrollCardRequest_ShouldHaveCorrectEqualsAndHashCode() {
        String cardId1 = "123456789";
        String cardId2 = "987654321";

        EnrollCardRequest enrollCardRequest1 = new EnrollCardRequest(cardId1);
        EnrollCardRequest enrollCardRequest2 = new EnrollCardRequest(cardId1);
        EnrollCardRequest enrollCardRequest3 = new EnrollCardRequest(cardId2);

        assertEquals(enrollCardRequest1, enrollCardRequest2);
        assertNotEquals(enrollCardRequest1, enrollCardRequest3);

        assertEquals(enrollCardRequest1.hashCode(), enrollCardRequest2.hashCode());
        assertNotEquals(enrollCardRequest1.hashCode(), enrollCardRequest3.hashCode());
    }
}