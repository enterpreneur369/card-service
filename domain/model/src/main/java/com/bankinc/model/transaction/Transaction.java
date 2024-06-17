package com.bankinc.model.transaction;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Transaction {
    private String id;
    private LocalDateTime date;
    private Long amount;
    private TransactionState state;
}
