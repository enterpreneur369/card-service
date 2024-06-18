package com.bankinc.jpa.impl;

import com.bankinc.jpa.helper.AdapterOperations;
import com.bankinc.jpa.model.CardEntity;
import com.bankinc.jpa.model.ClientEntity;
import com.bankinc.jpa.model.TransactionEntity;
import com.bankinc.jpa.repo.JPACardRepository;
import com.bankinc.jpa.repo.JPATransactionRepository;
import com.bankinc.model.card.Card;
import com.bankinc.model.card.gateways.CardOperations;
import com.bankinc.model.card.gateways.CardRepository;
import com.bankinc.model.client.Client;
import com.bankinc.model.transaction.Transaction;
import com.bankinc.model.transaction.gateways.TransactionRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JPATransactionRepositoryAdapter extends AdapterOperations<Transaction, TransactionEntity, String,
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
        return repository.findById(id).map(clientEntity -> mapper.map(clientEntity, Transaction.class))
                .orElse(Transaction.builder().build());
    }

    @Override
    public Transaction createTransaction(String cardId, Long price) {

        TransactionEntity transactionEntity = mapper.map(Transaction
                .builder()
                .amount(price)
                .build(), TransactionEntity.class);
        TransactionEntity savedEntity = repository.save(transactionEntity);
        return mapper.map(savedEntity, Transaction.class);
    }
}
