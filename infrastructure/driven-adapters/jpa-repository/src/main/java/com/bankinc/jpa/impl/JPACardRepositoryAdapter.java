package com.bankinc.jpa.impl;

import com.bankinc.jpa.helper.AdapterOperations;
import com.bankinc.jpa.model.CardEntity;
import com.bankinc.jpa.model.ClientEntity;
import com.bankinc.jpa.repo.JPACardRepository;
import com.bankinc.model.card.Card;
import com.bankinc.model.card.gateways.CardRepository;
import com.bankinc.model.client.Client;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPACardRepositoryAdapter extends AdapterOperations<Card, CardEntity, Integer, JPACardRepository>
 implements CardRepository
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
        return repository.findById(number).map(cardEntity -> mapper.map(cardEntity, Card.class))
                .orElse(Card.builder().build());
    }

    @Override
    public Card updateCard(Card card) {
        CardEntity clientEntity = mapper.map(card, CardEntity.class);
        CardEntity savedEntity = repository.save(clientEntity);
        return mapper.map(savedEntity, Card.class);
    }

    @Override
    public Card saveCard(Card card) {
        card.setId(null);
        CardEntity cardEntity = mapper.map(card, CardEntity.class);
        CardEntity savedEntity = repository.save(cardEntity);
        return mapper.map(savedEntity, Card.class);
    }

    @Override
    public Card getCardById(String id) {
        return new Card();
    }
}
