package com.bankinc.api.util;

public record PurchaseRequest(
        String cardId,
        Long price
) {
}
