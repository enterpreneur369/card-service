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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResponseExceptionHanlder {
    @ExceptionHandler(CardExpiredException.class)
    public ResponseEntity<CustomErrorRecord> handleCardExpiredException(CardExpiredException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(CardInactiveException.class)
    public ResponseEntity<CustomErrorRecord> handleCardInactiveException(CardInactiveException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(CardLockedException.class)
    public ResponseEntity<CustomErrorRecord> handleCardLockedException(CardLockedException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InsufficientErrorException.class)
    public ResponseEntity<CustomErrorRecord> handleInsufficientErrorException(InsufficientErrorException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(CardWithProductNorValidException.class)
    public ResponseEntity<CustomErrorRecord> handleCardWithProductNorValidException(CardWithProductNorValidException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<CustomErrorRecord> handleCardNotFoundException(CardNotFoundException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardFailedUpdateException.class)
    public ResponseEntity<CustomErrorRecord> handleCardFailedUpdateException(CardFailedUpdateException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CardAlreadyLockedException.class)
    public ResponseEntity<CustomErrorRecord> handleCardAlreadyLockedException(CardAlreadyLockedException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotValidBalanceException.class)
    public ResponseEntity<CustomErrorRecord> handleCardNotValidBalanceException(CardNotValidBalanceException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GeneralFailException.class)
    public ResponseEntity<CustomErrorRecord> handleGeneralFailBalanceException(GeneralFailException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<CustomErrorRecord> handleTransactionNotFoundException(TransactionNotFoundException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionAlreadyAnulatedException.class)
    public ResponseEntity<CustomErrorRecord> handleTransactionAlreadyAnulatedException(TransactionAlreadyAnulatedException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionAnulationTimeExceededException.class)
    public ResponseEntity<CustomErrorRecord> handleTransactionAnulationTimeExceededException(TransactionAnulationTimeExceededException ex, WebRequest request) {
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
