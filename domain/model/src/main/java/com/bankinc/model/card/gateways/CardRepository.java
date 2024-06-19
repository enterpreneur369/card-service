package com.bankinc.model.card.gateways;

import com.bankinc.model.card.Card;

public interface CardRepository {
    Card getCardByNumber(String number);
    Card updateCard(Card card);
    Card saveCard(Card card);
    Card getCardById(Integer id);
}
