package com.bankcore.jakarta.models;

import java.util.UUID;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // IMPORTANTE: O Hibernate exige isso

import com.bankcore.jakarta.enuns.UserRole;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(columnDefinition = "uuid") 
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    private Wallet wallet;

    public User(String name, String cpf, String email, String password, String phoneNumber) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
        this.role = UserRole.CUSTOMER;
        
        this.wallet = new Wallet();
        this.wallet.setUser(this);
    } 

    public void deactivate() {
        this.isActive = false;
    }

    public void updateRole(UserRole newRole) {
        this.role = newRole;
    }
}