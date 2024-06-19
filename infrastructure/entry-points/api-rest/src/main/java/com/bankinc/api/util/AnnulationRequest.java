package com.bankinc.api.util;

public record AnnulationRequest(
        String cardId,
        String transactionId) {
}
