package com.bankinc.usecase.transaction;

import com.bankinc.model.card.Card;
import com.bankinc.model.card.CardState;
import com.bankinc.model.card.gateways.CardRepository;
import com.bankinc.model.client.Client;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private TransactionUseCase transactionUseCase;

    private Card card;
    private Transaction transaction;
    private TransactionDTO transactionDTO;

    @BeforeEach
    void setUp() {
        card = new Card();
        card.setId(1);
        card.setBalance(500L);
        card.setClient(Client
                .builder()
                .id(1)
                .name("Jhon")
                .surname("Doe")
                .build());
        card.setDueDate(LocalDateTime.now().plusDays(1));
        card.setState(CardState.ACTIVE);

        transaction = Transaction.builder()
                .id("txn123")
                .amount(100L)
                .date(LocalDateTime.now())
                .state(TransactionState.ACTIVE)
                .card(card)
                .build();

        transactionDTO = TransactionDTO.builder()
                .id("txn123")
                .amount(100L)
                .date(LocalDateTime.now())
                .state(TransactionState.ACTIVE)
                .card(new CardDTO(
                        card.getId(),
                        card.getNumber(),
                        card.getClient().getName().concat(" ").concat(card.getClient().getSurname()),
                        card.getBalance(),
                        card.getCurrency()))
                .build();
    }

    @Test
    void createTransaction_ShouldCreateTransaction_WhenValid() {
        when(cardRepository.updateCard(any(Card.class))).thenReturn(card);
        when(transactionRepository.createTransaction(any(Transaction.class))).thenReturn(transaction);

        Transaction createdTransaction = transactionUseCase.createTransaction(transaction);

        assertNotNull(createdTransaction);
        assertEquals(transaction.getId(), createdTransaction.getId());
        verify(cardRepository).updateCard(card);
        verify(transactionRepository).createTransaction(transaction);
    }

    @Test
    void createTransaction_ShouldThrowInsufficientErrorException_WhenBalanceIsInsufficient() {
        card.setBalance(50L);
        assertThrows(InsufficientErrorException.class, () -> transactionUseCase.createTransaction(transaction));
    }

    @Test
    void createTransaction_ShouldThrowCardExpiredException_WhenCardIsExpired() {
        card.setDueDate(LocalDateTime.now().minusDays(1));
        assertThrows(CardExpiredException.class, () -> transactionUseCase.createTransaction(transaction));
    }

    @Test
    void createTransaction_ShouldThrowCardInactiveException_WhenCardIsInactive() {
        card.setState(CardState.INACTIVE);
        assertThrows(CardInactiveException.class, () -> transactionUseCase.createTransaction(transaction));
    }

    @Test
    void createTransaction_ShouldThrowCardLockedException_WhenCardIsLocked() {
        card.setState(CardState.LOCKED);
        assertThrows(CardLockedException.class, () -> transactionUseCase.createTransaction(transaction));
    }

    @Test
    void updateTransaction_ShouldUpdateTransaction_WhenValid() {
        when(transactionRepository.getTransactionById(transactionDTO.getId())).thenReturn(transactionDTO);
        when(cardRepository.getCardById(card.getId())).thenReturn(card);
        when(transactionRepository.updateTransaction(any(Transaction.class))).thenReturn(transactionDTO);

        TransactionDTO updatedTransaction = transactionUseCase.updateTransaction(transactionDTO);

        assertNotNull(updatedTransaction);
        assertEquals(transactionDTO.getId(), updatedTransaction.getId());
        verify(cardRepository).updateCard(card);
        verify(transactionRepository).updateTransaction(any(Transaction.class));
    }

    @Test
    void updateTransaction_ShouldThrowTransactionNotFoundException_WhenTransactionDoesNotExist() {
        when(transactionRepository.getTransactionById(transactionDTO.getId())).thenReturn(null);
        assertThrows(TransactionNotFoundException.class, () -> transactionUseCase.updateTransaction(transactionDTO));
    }

    @Test
    void updateTransaction_ShouldThrowTransactionAlreadyAnulatedException_WhenTransactionIsAlreadyAnulated() {
        transactionDTO.setState(TransactionState.CANCELLED);
        when(transactionRepository.getTransactionById(transactionDTO.getId())).thenReturn(transactionDTO);
        assertThrows(TransactionAlreadyAnulatedException.class, () -> transactionUseCase.updateTransaction(transactionDTO));
    }

    @Test
    void updateTransaction_ShouldThrowTransactionAnulationTimeExceededException_WhenMoreThan24HoursHavePassed() {
        transactionDTO.setDate(LocalDateTime.now().minusDays(1).minusHours(1));
        when(transactionRepository.getTransactionById(transactionDTO.getId())).thenReturn(transactionDTO);
        assertThrows(TransactionAnulationTimeExceededException.class, () -> transactionUseCase.updateTransaction(transactionDTO));
    }

    @Test
    void getTransactionById_ShouldReturnTransaction_WhenTransactionExists() {
        when(transactionRepository.getTransactionById(transactionDTO.getId())).thenReturn(transactionDTO);
        TransactionDTO foundTransaction = transactionUseCase.getTransactionById(transactionDTO.getId());
        assertNotNull(foundTransaction);
        assertEquals(transactionDTO.getId(), foundTransaction.getId());
    }

    @Test
    void getTransactionById_ShouldReturnNull_WhenTransactionDoesNotExist() {
        when(transactionRepository.getTransactionById(transactionDTO.getId())).thenReturn(null);
        TransactionDTO foundTransaction = transactionUseCase.getTransactionById(transactionDTO.getId());
        assertNull(foundTransaction);
    }
}
