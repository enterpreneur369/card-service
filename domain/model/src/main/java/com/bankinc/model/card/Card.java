package com.bankinc.model.card;
import com.bankinc.model.client.Client;
import com.bankinc.model.transaction.Transaction;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Card {
    private Integer id;
    private String number;
    private Client client;
    private LocalDateTime dueDate;
    private CardState state;
    private Long balance;
    private String currency;
    private List<Transaction> transactions;
}
