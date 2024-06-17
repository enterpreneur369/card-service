package com.bankinc.usecase.card;

import com.bankinc.model.card.Card;
import com.bankinc.model.card.gateways.CardOperations;
import com.bankinc.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class CardUseCase {

    private final CardRepository repository;
    private final CardOperations operations;

    public Card generateCardNumber(String idProduct) {
        return operations.generateCardNumber(idProduct);
    }

    public Card lockCard(String cardId) {
        return operations.lockCard(cardId);
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
