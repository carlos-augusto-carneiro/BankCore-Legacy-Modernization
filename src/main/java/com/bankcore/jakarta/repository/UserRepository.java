package com.bankcore.jakarta.repository;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bankcore.jakarta.interfaces.IUserRepository;
import com.bankcore.jakarta.models.User;

public class UserRepository implements IUserRepository {

    @PersistenceContext(unitName = "BankCorePU")
    private EntityManager em;


    @Override
    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        try {
            User user = em.createQuery("SELECT u FROM User u WHERE u.cpf = :cpf", User.class)
                          .setParameter("cpf", cpf)
                          .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                          .setParameter("email", email)
                          .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(UUID id) {
        User user = em.find(User.class, id);
        if (user != null) {
            user.setActive(false);
        }
    }

    @Override
    public void update(UUID id, User user) {
        User existingUser = em.find(User.class, id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
        }
    }

}
