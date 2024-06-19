package com.bankinc.model.transaction.gateways;

import com.bankinc.model.dto.TransactionDTO;
import com.bankinc.model.transaction.Transaction;

public interface TransactionRepository {
    TransactionDTO getTransactionById(String id);
    Transaction createTransaction(Transaction transaction);
}
