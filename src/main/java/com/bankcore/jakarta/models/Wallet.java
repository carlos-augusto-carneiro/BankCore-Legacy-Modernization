package com.bankcore.jakarta.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor 
public class Wallet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private int agency;
    
    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private User user;

    @OneToMany(mappedBy = "wallet_origen")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "wallet_destination")
    private List<Transaction> receivedTransactions;

    @Column(nullable = false)
    private boolean isActive;

    public Wallet(User user) {
        this.agency = generatedAgency();
        this.accountNumber = generateAccountNumber();
        this.balance = BigDecimal.valueOf(0);
        this.user = user;
        this.isActive = true;
    }

    public int generatedAgency() {
        return 0012; 
    }

    public static String generateAccountNumber() {
        Random random = new Random();
        int baseNumber = 1000000 + random.nextInt(9000000); 
        
        int dv = baseNumber % 11;
        if (dv == 10) dv = 0;

        return baseNumber + "-" + dv;
    }

}