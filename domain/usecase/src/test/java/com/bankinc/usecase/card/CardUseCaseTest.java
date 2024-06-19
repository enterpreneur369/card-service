package com.bankinc.usecase.card;

import com.bankinc.model.card.Card;
import com.bankinc.model.card.CardState;
import com.bankinc.model.card.gateways.CardRepository;
import com.bankinc.model.exception.CardAlreadyLockedException;
import com.bankinc.model.exception.CardFailedUpdateException;
import com.bankinc.model.exception.CardInactiveStateException;
import com.bankinc.model.exception.CardNotFoundException;
import com.bankinc.model.exception.CardNotValidBalanceException;
import com.bankinc.model.exception.CardWithProductNorValidException;
import com.bankinc.model.exception.GeneralFailException;
import com.bankinc.model.exception.InsufficientErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardUseCaseTest {

    @Mock
    private CardRepository repository;

    @InjectMocks
    private CardUseCase cardUseCase;

    private Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
    }

    @Test
    void generateCardNumber_ValidProductId_ShouldGenerateCardNumber() {

        String productId = "123456";
        Card card = new Card();

        Card generatedCard = cardUseCase.generateCardNumber(productId, card);

        assertNotNull(generatedCard.getNumber());
        assertTrue(generatedCard.getNumber().startsWith("123456"));
    }

    @Test
    void generateCardNumber_InvalidProductId_ShouldThrowException() {

        String invalidProductId = "abc";
        Card card = new Card();

        assertThrows(CardWithProductNorValidException.class, () -> {
            cardUseCase.generateCardNumber(invalidProductId, card);
        });
    }

    @Test
    void activateCard_InactiveCard_ShouldActivateCard() {

        Card inactiveCard = new Card();
        inactiveCard.setState(CardState.INACTIVE);

        Card activatedCard = cardUseCase.activateCard(inactiveCard);

        assertEquals(CardState.ACTIVE, activatedCard.getState());
    }

    @Test
    void activateCard_ActiveCard_ShouldThrowException() {

        Card activeCard = new Card();
        activeCard.setState(CardState.ACTIVE);

        assertThrows(CardInactiveStateException.class, () -> {
            cardUseCase.activateCard(activeCard);
        });
    }

    @Test
    void getCardByNumber_CardFound_ShouldReturnCard() {

        String cardNumber = "1234567890123456";
        Card mockCard = new Card();
        mockCard.setId(1);
        when(repository.getCardByNumber(cardNumber)).thenReturn(mockCard);

        Card retrievedCard = cardUseCase.getCardByNumber(cardNumber);

        assertNotNull(retrievedCard);
        assertEquals(1, retrievedCard.getId());
    }

    @Test
    void getCardByNumber_CardNotFound_ShouldThrowException() {
        String nonExistentCardNumber = "9999999999999999";
        when(repository.getCardByNumber(nonExistentCardNumber)).thenReturn(Card.builder().build());

        assertThrows(CardNotFoundException.class, () -> {
            cardUseCase.getCardByNumber(nonExistentCardNumber);
        });
    }

    @Test
    void updateCard_SuccessfulUpdate_ShouldReturnUpdatedCard() {
        Card cardToUpdate = new Card();
        cardToUpdate.setId(1);
        cardToUpdate.setState(CardState.ACTIVE);

        when(repository.updateCard(cardToUpdate)).thenReturn(cardToUpdate);

        Card updatedCard = cardUseCase.updateCard(cardToUpdate);

        assertNotNull(updatedCard);
        assertEquals(CardState.ACTIVE, updatedCard.getState());
    }

    @Test
    void updateCard_FailedUpdate_ShouldThrowCardFailedUpdateException() {
        Card cardToUpdate = new Card();
        cardToUpdate.setId(1);
        cardToUpdate.setState(CardState.ACTIVE);

        when(repository.updateCard(cardToUpdate)).thenThrow(new RuntimeException("Failed to update"));

        assertThrows(CardFailedUpdateException.class, () -> {
            cardUseCase.updateCard(cardToUpdate);
        });
    }

    @Test
    void getCardById_CardFound_ShouldReturnCard() {
        int cardId = 1;
        Card mockCard = new Card();
        mockCard.setId(cardId);

        when(repository.getCardById(cardId)).thenReturn(mockCard);

        Card retrievedCard = cardUseCase.getCardById(cardId);

        assertNotNull(retrievedCard);
        assertEquals(cardId, retrievedCard.getId());
    }

    @Test
    void getCardById_CardNotFound_ShouldThrowCardNotFoundException() {
        int nonExistentCardId = 999;
        when(repository.getCardById(nonExistentCardId)).thenReturn(null);

        assertThrows(CardNotFoundException.class, () -> {
            cardUseCase.getCardById(nonExistentCardId);
        });
    }

    @Test
    void lockCard_InactiveCard_ShouldLockCard() {
        Card inactiveCard = new Card();
        inactiveCard.setState(CardState.INACTIVE);

        Card lockedCard = cardUseCase.lockCard(inactiveCard);

        assertEquals(CardState.LOCKED, lockedCard.getState());
    }

    @Test
    void lockCard_LockedCard_ShouldThrowCardAlreadyLockedException() {

        Card lockedCard = new Card();
        lockedCard.setState(CardState.LOCKED);


        assertThrows(CardAlreadyLockedException.class, () -> {
            cardUseCase.lockCard(lockedCard);
        });
    }

    @Test
    void reloadBalance_ValidAmount_ShouldIncreaseBalance() {

        Card cardToUpdateBalance = new Card();
        cardToUpdateBalance.setBalance(100L);
        String balanceToAdd = "50";

        Card reloadedCard = cardUseCase.reloadBalance(cardToUpdateBalance, balanceToAdd);

        assertEquals(150, reloadedCard.getBalance());
    }

    @Test
    void reloadBalance_NegativeAmount_ShouldThrowInsufficientErrorException() {
        Card cardToUpdateBalance = new Card();
        cardToUpdateBalance.setBalance(100L);
        String balanceToAdd = "-50";

        assertThrows(InsufficientErrorException.class, () -> {
            cardUseCase.reloadBalance(cardToUpdateBalance, balanceToAdd);
        });
    }

    @Test
    void reloadBalance_InvalidAmountFormat_ShouldThrowCardNotValidBalanceException() {
        Card cardToUpdateBalance = new Card();
        String invalidBalance = "abc";

        assertThrows(CardNotValidBalanceException.class, () -> {
            cardUseCase.reloadBalance(cardToUpdateBalance, invalidBalance);
        });
    }

    @Test
    void reloadBalance_FailedUpdate_ShouldThrowGeneralFailException() {

        Card cardToUpdateBalance = new Card();
        String validBalance = "50";

        assertThrows(GeneralFailException.class, () -> {
            cardUseCase.reloadBalance(cardToUpdateBalance, validBalance);
        });
    }




}
