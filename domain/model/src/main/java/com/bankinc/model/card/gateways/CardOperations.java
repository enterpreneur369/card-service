package com.bankinc.model.card.gateways;

import com.bankinc.model.card.Card;

public interface CardOperations {
    Card generateCardNumber(String idProduct);
    Card lockCard(String cardId);
}
