package com.bankinc.usecase.transaction;

import com.bankinc.model.transaction.Transaction;
import com.bankinc.model.transaction.gateways.TransactionRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class TransactionUseCase {
    private final TransactionRepository repository;

    Transaction getTransactionById(String id) {
        return repository.getTransactionById(id);
    }

    Transaction createTransaction(String cardId, Long price) {
        return repository.createTransaction(cardId, price);
    }
}
