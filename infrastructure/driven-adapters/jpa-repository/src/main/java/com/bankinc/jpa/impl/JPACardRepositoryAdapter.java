package com.bankinc.jpa.impl;

import com.bankinc.jpa.helper.AdapterOperations;
import com.bankinc.jpa.model.CardEntity;
import com.bankinc.jpa.repo.JPACardRepository;
import com.bankinc.model.card.Card;
import com.bankinc.model.card.gateways.CardOperations;
import com.bankinc.model.card.gateways.CardRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPACardRepositoryAdapter extends AdapterOperations<Card, CardEntity, Integer, JPACardRepository>
 implements CardRepository, CardOperations
{

    public JPACardRepositoryAdapter(JPACardRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Card.class));
    }

    @Override
    public Card getCardByNumber(Integer number) {
        return new Card();
    }

    @Override
    public Card updateCard(Card card) {
        return new Card();
    }

    @Override
    public Card getCardById(String id) {
        return new Card();
    }

    @Override
    public Card generateCardNumber(String idProduct) {
        return new Card();
    }

    @Override
    public Card lockCard(String cardId) {
        return new Card();
    }
}
