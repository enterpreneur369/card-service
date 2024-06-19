package com.bankinc.usecase.transaction;

import com.bankinc.model.card.Card;
import com.bankinc.model.card.CardState;
import com.bankinc.model.card.gateways.CardRepository;
import com.bankinc.model.dto.CardDTO;
import com.bankinc.model.dto.TransactionDTO;
import com.bankinc.model.exception.CardExpiredException;
import com.bankinc.model.exception.CardInactiveException;
import com.bankinc.model.exception.CardLockedException;
import com.bankinc.model.exception.InsufficientErrorException;
import com.bankinc.model.exception.TransactionAlreadyAnulatedException;
import com.bankinc.model.exception.TransactionAnulationTimeExceededException;
import com.bankinc.model.exception.TransactionNotFoundException;
import com.bankinc.model.transaction.Transaction;
import com.bankinc.model.transaction.TransactionState;
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

    public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
        TransactionDTO existingTransactionDTO = getTransactionById(transactionDTO.getId());

        if (existingTransactionDTO == null) {
            throw new TransactionNotFoundException("Transaction not found for ID: " + transactionDTO.getId());
        }
        if (existingTransactionDTO.getState() == TransactionState.CANCELLED) {
            throw new TransactionAlreadyAnulatedException("Transaction is already anulated.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime transactionDate = existingTransactionDTO.getDate();
        if (transactionDate.plusHours(24).isBefore(now)) {
            throw new TransactionAnulationTimeExceededException("Transaction cannot be anulated after 24 hours.");
        }

        existingTransactionDTO.setState(TransactionState.CANCELLED);

        CardDTO cardDTO = existingTransactionDTO.getCard();
        Card card = cardRepository.getCardById(cardDTO.getId());
        card.setBalance(card.getBalance() + existingTransactionDTO.getAmount());

        cardRepository.updateCard(card);

        return transactionRepository.updateTransaction(
                Transaction
                        .builder()
                        .id(existingTransactionDTO.getId())
                        .date(existingTransactionDTO.getDate())
                        .state(existingTransactionDTO.getState())
                        .amount(existingTransactionDTO.getAmount())
                        .card(card)
                        .build());
    }
}
