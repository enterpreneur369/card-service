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
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class CardUseCase {

    private final CardRepository repository;
    private final Random random = new Random();

    public Card generateCardNumber(String productId, Card card) {
        try {
            String productIdDigits = String.format("%06d", Integer.parseInt(productId));
            String randomDigits = generateRandomDigits(10);
            String generatedNumber = productIdDigits + randomDigits;
            card.setNumber(generatedNumber);
            return card;
        } catch (NumberFormatException e) {
            throw new CardWithProductNorValidException("Product ID must be a valid number.");
        }
    }

    private String generateRandomDigits(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    public Card activateCard(Card card) {
        if (card.getState() != CardState.INACTIVE) {
            throw new CardInactiveStateException("Card must be inactive to activate.");
        }
        card.setState(CardState.ACTIVE);
        return card;
    }

    public Card getCardByNumber(String number) {
        Card card = repository.getCardByNumber(number);
        if (card.getId() == null) {
            throw new CardNotFoundException("Card not found for number: " + number);
        }
        return card;
    }

    public Card updateCard(Card card) {
        try {
            return repository.updateCard(card);
        } catch (Exception e) {
            throw new CardFailedUpdateException("Failed to update card");
        }
    }

    public Card getCardById(Integer id) {
        Card card = repository.getCardById(id);
        if (card == null) {
            throw new CardNotFoundException("Card not found for ID: " + id);
        }
        return card;
    }

    public Card lockCard(Card cardToLock) {
        if (cardToLock.getState() == CardState.LOCKED) {
            throw new CardAlreadyLockedException("Card is already locked.");
        }
        cardToLock.setState(CardState.LOCKED);
        return cardToLock;
    }

    public Card reloadBalance(Card cardToUpdateBalance, String balance) {
        try {
            long amount = Long.parseLong(balance);
            if (amount <= 0) {
                throw new InsufficientErrorException("Balance amount must be greater than zero.");
            }
            cardToUpdateBalance.setBalance(cardToUpdateBalance.getBalance() + amount);
            return cardToUpdateBalance;
        } catch(InsufficientErrorException ex){
            throw new InsufficientErrorException(ex.getMessage());
        } catch (NumberFormatException e) {
            throw new CardNotValidBalanceException("Invalid balance amount: " + balance);
        } catch (Exception e) {
            throw new GeneralFailException("Failed to reload balance.");
        }
    }
}

