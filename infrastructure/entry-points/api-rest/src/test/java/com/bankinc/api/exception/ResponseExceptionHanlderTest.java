package com.bankinc.api.exception;

import com.bankinc.model.exception.CardAlreadyLockedException;
import com.bankinc.model.exception.CardExpiredException;
import com.bankinc.model.exception.CardFailedUpdateException;
import com.bankinc.model.exception.CardInactiveException;
import com.bankinc.model.exception.CardLockedException;
import com.bankinc.model.exception.CardNotFoundException;
import com.bankinc.model.exception.CardNotValidBalanceException;
import com.bankinc.model.exception.CardWithProductNorValidException;
import com.bankinc.model.exception.CustomErrorRecord;
import com.bankinc.model.exception.GeneralFailException;
import com.bankinc.model.exception.InsufficientErrorException;
import com.bankinc.model.exception.TransactionAlreadyAnulatedException;
import com.bankinc.model.exception.TransactionAnulationTimeExceededException;
import com.bankinc.model.exception.TransactionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResponseExceptionHanlderTest {

    private ResponseExceptionHanlder responseExceptionHanlder;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        responseExceptionHanlder = new ResponseExceptionHanlder();
        webRequest = Mockito.mock(WebRequest.class);
    }

    @Test
    void handleInsufficientErrorException_ShouldReturnExpectationFailed() {
        InsufficientErrorException exception = new InsufficientErrorException("Insufficient balance");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleInsufficientErrorException(exception, webRequest);

        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals("Insufficient balance", response.getBody().message());
    }

    @Test
    void handleCardInactiveException_ShouldReturnExpectationFailed() {
        CardInactiveException exception = new CardInactiveException("Card is inactive");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleCardInactiveException(exception, webRequest);

        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals("Card is inactive", response.getBody().message());
    }

    @Test
    void handleCardLockedException_ShouldReturnForbidden() {
        CardLockedException exception = new CardLockedException("Card is locked");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleCardLockedException(exception, webRequest);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Card is locked", response.getBody().message());
    }

    @Test
    void handleCardWithProductNorValidException_ShouldReturnBadRequest() {
        CardWithProductNorValidException exception = new CardWithProductNorValidException("Product not valid");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleCardWithProductNorValidException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Product not valid", response.getBody().message());
    }

    @Test
    void handleCardNotFoundException_ShouldReturnNotFound() {
        CardNotFoundException exception = new CardNotFoundException("Card not found");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleCardNotFoundException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Card not found", response.getBody().message());
    }

    @Test
    void handleCardFailedUpdateException_ShouldReturnInternalServerError() {
        CardFailedUpdateException exception = new CardFailedUpdateException("Update failed");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleCardFailedUpdateException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Update failed", response.getBody().message());
    }

    @Test
    void handleCardAlreadyLockedException_ShouldReturnBadRequest() {
        CardAlreadyLockedException exception = new CardAlreadyLockedException("Card already locked");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleCardAlreadyLockedException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Card already locked", response.getBody().message());
    }

    @Test
    void handleCardNotValidBalanceException_ShouldReturnBadRequest() {
        CardNotValidBalanceException exception = new CardNotValidBalanceException("Balance not valid");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleCardNotValidBalanceException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Balance not valid", response.getBody().message());
    }

    @Test
    void handleGeneralFailException_ShouldReturnInternalServerError() {
        GeneralFailException exception = new GeneralFailException("General fail");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleGeneralFailBalanceException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("General fail", response.getBody().message());
    }

    @Test
    void handleTransactionNotFoundException_ShouldReturnInternalServerError() {
        TransactionNotFoundException exception = new TransactionNotFoundException("Transaction not found");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleTransactionNotFoundException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Transaction not found", response.getBody().message());
    }

    @Test
    void handleTransactionAlreadyAnulatedException_ShouldReturnInternalServerError() {
        TransactionAlreadyAnulatedException exception = new TransactionAlreadyAnulatedException("Transaction already anulated");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleTransactionAlreadyAnulatedException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Transaction already anulated", response.getBody().message());
    }

    @Test
    void handleTransactionAnulationTimeExceededException_ShouldReturnInternalServerError() {
        TransactionAnulationTimeExceededException exception = new TransactionAnulationTimeExceededException("Anulation time exceeded");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleTransactionAnulationTimeExceededException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Anulation time exceeded", response.getBody().message());
    }

    @Test
    void handleCardExpiredException_ShouldReturnExpectationFailed() {
        CardExpiredException exception = new CardExpiredException("Card expired");

        ResponseEntity<CustomErrorRecord> response = responseExceptionHanlder.handleCardExpiredException(exception, webRequest);

        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals("Card expired", response.getBody().message());
    }
}
