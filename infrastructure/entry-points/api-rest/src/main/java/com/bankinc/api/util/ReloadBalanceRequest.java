package com.bankinc.api.util;

public record ReloadBalanceRequest(
        String cardId,
        String balance
) {
}
