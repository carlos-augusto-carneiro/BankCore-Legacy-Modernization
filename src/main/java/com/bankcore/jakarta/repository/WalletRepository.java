package com.bankcore.jakarta.repository;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bankcore.jakarta.interfaces.IWalletRepository;
import com.bankcore.jakarta.models.Wallet;

public class WalletRepository implements IWalletRepository {

    @PersistenceContext(unitName = "BankCorePU")
    private EntityManager em;

    @Override
    @Transactional
    public Wallet save(Wallet wallet) {
        em.persist(wallet);
        return wallet;
    }

    @Override
    public Optional<Wallet> findById(UUID id) {
        return Optional.ofNullable(em.find(Wallet.class, id));
    }

    @Override
    public Optional<Wallet> findByUserCpf(String cpf) {
        try {
            Wallet wallet = em.createQuery("SELECT w FROM Wallet w WHERE w.user.cpf = :cpf", Wallet.class)
                              .setParameter("cpf", cpf)
                              .getSingleResult();
            return Optional.of(wallet);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        try {
            Long count = em.createQuery("SELECT COUNT(w) FROM Wallet w WHERE w.accountNumber = :accountNumber", Long.class)
                           .setParameter("accountNumber", accountNumber)
                           .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Wallet> findByAccountNumber(String accountNumber) {
        try {
            Wallet wallet = em.createQuery("SELECT w FROM Wallet w WHERE w.accountNumber = :accountNumber", Wallet.class)
                              .setParameter("accountNumber", accountNumber)
                              .getSingleResult();
            return Optional.of(wallet);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
