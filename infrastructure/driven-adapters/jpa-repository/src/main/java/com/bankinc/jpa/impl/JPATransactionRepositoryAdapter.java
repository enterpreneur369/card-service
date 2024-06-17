package com.bankinc.jpa.impl;

import com.bankinc.jpa.helper.AdapterOperations;
import com.bankinc.jpa.model.CardEntity;
import com.bankinc.jpa.model.TransactionEntity;
import com.bankinc.jpa.repo.JPACardRepository;
import com.bankinc.jpa.repo.JPATransactionRepository;
import com.bankinc.model.card.Card;
import com.bankinc.model.card.gateways.CardOperations;
import com.bankinc.model.card.gateways.CardRepository;
import com.bankinc.model.transaction.Transaction;
import com.bankinc.model.transaction.gateways.TransactionRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPATransactionRepositoryAdapter extends AdapterOperations<Transaction, TransactionEntity, Integer,
        JPATransactionRepository>
 implements TransactionRepository
{

    public JPATransactionRepositoryAdapter(JPATransactionRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Transaction.class));
    }

    @Override
    public Transaction getTransactionById(String id) {
        return new Transaction();
    }

    @Override
    public Transaction createTransaction(String cardId, Long price) {
        return new Transaction();
    }
}
