package com.bankinc.jpa.impl;

import com.bankinc.jpa.helper.AdapterOperations;
import com.bankinc.jpa.model.TransactionEntity;
import com.bankinc.jpa.repo.JPATransactionRepository;
import com.bankinc.model.dto.CardDTO;
import com.bankinc.model.dto.TransactionDTO;
import com.bankinc.model.exception.TransactionNotFoundException;
import com.bankinc.model.transaction.Transaction;
import com.bankinc.model.transaction.TransactionState;
import com.bankinc.model.transaction.gateways.TransactionRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public TransactionDTO getTransactionById(String id) {
        return repository.findById(id)
                .map(transactionEntity -> TransactionDTO.builder()
                        .id(transactionEntity.getId())
                        .amount(transactionEntity.getAmount())
                        .date(transactionEntity.getDate())
                        .state(transactionEntity.getState())
                        .card(CardDTO.builder()
                                .id(transactionEntity.getCard().getId())
                                .balance(transactionEntity.getCard().getBalance())
                                .clientName(transactionEntity.getCard().getClient().getName()
                                        .concat(" ")
                                        .concat(transactionEntity.getCard().getClient().getSurname()))
                                .currency(transactionEntity.getCard().getCurrency())
                                .number(transactionEntity.getCard().getNumber())
                                .build())
                        .build())
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found for id: " + id));
    }



    @Override
    public Transaction createTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = mapper.map(Transaction
                .builder()
                        .id(null)
                        .card(transaction.getCard())
                        .date(transaction.getDate())
                        .state(transaction.getState())
                        .amount(transaction.getAmount())
                .build(), TransactionEntity.class);
        TransactionEntity savedEntity = repository.save(transactionEntity);
        return mapper.map(savedEntity, Transaction.class);
    }

    @Override
    public TransactionDTO updateTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = mapper.map(Transaction
                .builder()
                .id(transaction.getId())
                .card(transaction.getCard())
                .date(transaction.getDate())
                .state(transaction.getState())
                .amount(transaction.getAmount())
                .build(), TransactionEntity.class);
        TransactionEntity savedEntity = repository.save(transactionEntity);
        return TransactionDTO
                .builder()
                .id(savedEntity.getId())
                .card(CardDTO
                        .builder()
                        .id(savedEntity.getCard().getId())
                        .number(savedEntity.getCard().getNumber())
                        .currency(savedEntity.getCard().getCurrency())
                        .balance(savedEntity.getCard().getBalance())
                        .clientName(savedEntity.getCard().getClient()
                                .getName().concat(" ")
                                .concat(savedEntity.getCard().getClient().getSurname()))
                        .build())
                .date(savedEntity.getDate())
                .amount(savedEntity.getAmount())
                .state(savedEntity.getState())
                .build();
    }

}
