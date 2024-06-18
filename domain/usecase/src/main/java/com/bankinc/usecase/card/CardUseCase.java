package com.bankinc.usecase.card;

import com.bankinc.model.card.Card;
import com.bankinc.model.card.gateways.CardOperations;
import com.bankinc.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class CardUseCase {

    private final CardRepository repository;

    public Card generateCardNumber(String idProduct) {
        return Card.builder().build();
    }
    public Card lockCard(String cardId) {
        return Card.builder().build();
    }
    public Card getCardByNumber(Integer number) {
        return repository.getCardByNumber(number);
    }

    public Card updateCard(Card card) {
        return repository.updateCard(card);
    }

    public Card getCardById(String id) {
        return repository.getCardById(id);
    }
}
