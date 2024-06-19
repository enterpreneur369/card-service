package com.bankinc.model.dto;

import com.bankinc.model.transaction.TransactionState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionDTO {
    private String id;
    private CardDTO card;
    private LocalDateTime date;
    private Long amount;
    private TransactionState state;
}
