package com.bankinc.api;
import com.bankinc.api.util.EnrollCardRequest;
import com.bankinc.api.util.PurchaseRequest;
import com.bankinc.api.util.ReloadBalanceRequest;
import com.bankinc.model.card.Card;
import com.bankinc.model.card.CardState;
import com.bankinc.model.dto.TransactionDTO;
import com.bankinc.model.transaction.Transaction;
import com.bankinc.model.transaction.TransactionState;
import com.bankinc.usecase.card.CardUseCase;
import com.bankinc.usecase.transaction.TransactionUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.bankinc.model.constants.Constants.BALANCE_RELOADED_SUCCESS;
import static com.bankinc.model.constants.Constants.CARD_LOCKED_SUCCESS;
import static com.bankinc.model.constants.Constants.CARD_NOT_FOUND;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final CardUseCase cardUseCase;
    private final TransactionUseCase transactionUseCase;


    @GetMapping(path = "/card/{productId}/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Card generateCardNumber(@PathVariable("productId") String productId,
                                   @PathVariable("number") Integer number) {
        Card cardToUpdate = cardUseCase.getCardById(number);
        cardToUpdate = cardUseCase.generateCardNumber(productId, cardToUpdate);
        return cardUseCase.updateCard(cardToUpdate);
    }

    @PostMapping("/card/enroll")
    public ResponseEntity<String> enrollCard(@RequestBody EnrollCardRequest request) {

        String cardId = request.cardId();
        Card cardToUpdate = cardUseCase.getCardByNumber(cardId);
        cardToUpdate = cardUseCase.activateCard(cardToUpdate);
        cardUseCase.updateCard(cardToUpdate);
        return ResponseEntity.ok("Card activated successfully.");
    }

    @DeleteMapping("/card/{cardId}")
    public ResponseEntity<String> blockCard(@PathVariable("cardId") String cardId) {
        Card cardToUpdate = cardUseCase.getCardByNumber(cardId);
        if (cardToUpdate.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CARD_NOT_FOUND);
        }

        cardToUpdate = cardUseCase.lockCard(cardToUpdate);
        cardUseCase.updateCard(cardToUpdate);
        return ResponseEntity.ok(CARD_LOCKED_SUCCESS);
    }

    @PostMapping("/card/balance")
    public ResponseEntity<String> reloadBalance(@RequestBody ReloadBalanceRequest request) {

        try {
            Card card = cardUseCase.getCardByNumber(request.cardId());
            if (card.getId() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CARD_NOT_FOUND);
            }
            card = cardUseCase.reloadBalance(card, request.balance());
            cardUseCase.updateCard(card);
            return ResponseEntity.ok(BALANCE_RELOADED_SUCCESS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/card/balance/{cardId}")
    public ResponseEntity<String> getBalance(@PathVariable("cardId") String cardId) {
        try {
            Card card = cardUseCase.getCardByNumber(cardId);
            if (card.getId() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found.");
            }
            return ResponseEntity.ok("Balance: " + card.getBalance());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/transaction/purchase")
    public ResponseEntity<Transaction> purchase(@RequestBody PurchaseRequest request) {

        Card card = cardUseCase.getCardByNumber(request.cardId());
        Transaction transaction = transactionUseCase.createTransaction(Transaction
                .builder()
                        .amount(request.price())
                        .state(TransactionState.ACTIVE)
                        .date(LocalDateTime.now())
                        .card(card)
                .build());
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable("transactionId") String transactionId) {
        TransactionDTO transaction = transactionUseCase.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }
}
