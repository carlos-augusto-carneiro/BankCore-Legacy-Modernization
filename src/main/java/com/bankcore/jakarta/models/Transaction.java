package com.bankcore.jakarta.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;

import com.bankcore.jakarta.enuns.TypeTransaction;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "wallet_origen_id", nullable = false)
    private Wallet wallet_origen;

    @ManyToOne
    @JoinColumn(name = "wallet_destination_id", nullable = false)
    private Wallet wallet_destination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeTransaction typeTransaction;

    public Transaction(BigDecimal amount, Wallet wallet_origen, Wallet wallet_destination, TypeTransaction typeTransaction) {
        this.amount = amount;
        this.wallet_origen = wallet_origen;
        this.wallet_destination = wallet_destination;
        this.typeTransaction = typeTransaction;
        this.timestamp = LocalDateTime.now();
    }
}