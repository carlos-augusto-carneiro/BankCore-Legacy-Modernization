package com.bankcore.jakarta.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.bankcore.jakarta.models.Transaction;

public interface ITransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findByWalletId(UUID walletId);
    List<Transaction> findByWalletIdAndPeriod(UUID walletId, LocalDateTime start, LocalDateTime end);
}