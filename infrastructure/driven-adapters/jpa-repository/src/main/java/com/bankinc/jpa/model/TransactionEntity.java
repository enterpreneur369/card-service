package com.bankinc.jpa.model;

import com.bankinc.model.card.CardState;
import com.bankinc.model.transaction.TransactionState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TransactionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "com.bankinc.jpa.util.UUIDGenerator"
    )
    private String id;
    private LocalDateTime date;
    private Long amount;
    private TransactionState state;
}
