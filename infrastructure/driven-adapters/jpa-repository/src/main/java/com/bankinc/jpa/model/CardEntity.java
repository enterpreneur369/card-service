package com.bankinc.jpa.model;

import com.bankinc.model.card.CardState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = true)
    private String number;
    private LocalDateTime dueDate;
    private CardState state;
    private Long balance;
    private String currency;
}
