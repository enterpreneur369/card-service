package com.bankinc.usecase.transaction;

import com.bankinc.model.card.Card;
import com.bankinc.model.card.CardState;
import com.bankinc.model.card.gateways.CardRepository;
import com.bankinc.model.dto.TransactionDTO;
import com.bankinc.model.exception.CardExpiredException;
import com.bankinc.model.exception.CardInactiveException;
import com.bankinc.model.exception.CardLockedException;
import com.bankinc.model.exception.InsufficientErrorException;
import com.bankinc.model.transaction.Transaction;
import com.bankinc.model.transaction.gateways.TransactionRepository;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TransactionUseCase {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    public TransactionDTO getTransactionById(String id) {
        return transactionRepository.getTransactionById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        Card card = transaction.getCard();

        if (card.getBalance() < transaction.getAmount()) {
            throw new InsufficientErrorException("Insufficient balance to process the transaction.");
        }

        if (card.getDueDate().isBefore(LocalDateTime.now())) {
            throw new CardExpiredException("Card has expired and cannot be used for transactions.");
        }

        if (card.getState().equals(CardState.INACTIVE)) {
            throw new CardInactiveException("Card is not active and cannot be used for transactions.");
        }

        if (card.getState().equals(CardState.LOCKED)) {
            throw new CardLockedException("Card is blocked and cannot be used for transactions.");
        }

        card.setBalance(card.getBalance() - transaction.getAmount());
        card = cardRepository.updateCard(card);

        transaction.setCard(card);
        return transactionRepository.createTransaction(transaction);
    }
}
