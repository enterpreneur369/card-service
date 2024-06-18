package com.bankinc.model.card.gateways;

import com.bankinc.model.card.Card;

public interface CardRepository {
    Card getCardByNumber(Integer number);
    Card updateCard(Card card);
    Card saveCard(Card card);
    Card getCardById(String id);
}
