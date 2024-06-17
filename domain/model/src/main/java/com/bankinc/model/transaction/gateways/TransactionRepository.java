package com.bankinc.model.transaction.gateways;

import com.bankinc.model.transaction.Transaction;

public interface TransactionRepository {
    Transaction getTransactionById(String id);
    Transaction createTransaction(String cardId, Long price);
}
