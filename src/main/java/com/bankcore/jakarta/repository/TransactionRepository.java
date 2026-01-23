package com.bankcore.jakarta.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bankcore.jakarta.interfaces.ITransactionRepository;
import com.bankcore.jakarta.models.Transaction;

public class TransactionRepository implements ITransactionRepository {

    @PersistenceContext(unitName = "BankCorePU")
    private EntityManager em;

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        em.persist(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> findByWalletId(UUID walletId) {
        return em.createQuery("SELECT t FROM Transaction t WHERE t.wallet.id = :walletId", Transaction.class)
                 .setParameter("walletId", walletId)
                 .getResultList();
    }

    @Override
    public List<Transaction> findByWalletIdAndPeriod(UUID walletId, LocalDateTime start, LocalDateTime end) {
        return em.createQuery("SELECT t FROM Transaction t WHERE t.wallet.id = :walletId AND t.timestamp BETWEEN :start AND :end", Transaction.class)
                 .setParameter("walletId", walletId)
                 .setParameter("start", start)
                 .setParameter("end", end)
                 .getResultList();
    }

}
