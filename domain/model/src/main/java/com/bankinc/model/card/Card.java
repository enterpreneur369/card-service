package com.bankinc.model.card;
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
public class Card {
    private Integer id;
    private LocalDateTime dueDate;
    private CardState state;
    private Long balance;
    private String currency;
}
