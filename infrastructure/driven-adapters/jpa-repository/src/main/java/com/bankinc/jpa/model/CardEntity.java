package com.bankinc.jpa.model;

import com.bankinc.model.card.CardState;
import com.bankinc.model.client.Client;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = true, unique = true, length = 16)
    private String number;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
    private LocalDateTime dueDate;
    private CardState state;
    private Long balance;
    private String currency;
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionEntity> transactions;
}