package com.bankcore.jakarta.interfaces;

import java.util.Optional;
import java.util.UUID;
import com.bankcore.jakarta.models.Wallet;

public interface IWalletRepository {

    Wallet save(Wallet wallet);
    
    Optional<Wallet> findById(UUID id);
    Optional<Wallet> findByUserCpf(String cpf); 
    boolean existsByAccountNumber(String accountNumber);
    Optional<Wallet> findByAccountNumber(String accountNumber); 
}