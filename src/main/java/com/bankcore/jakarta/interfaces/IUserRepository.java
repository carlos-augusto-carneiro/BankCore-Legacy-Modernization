package com.bankcore.jakarta.interfaces;

import java.util.Optional;
import java.util.UUID;

import com.bankcore.jakarta.models.User;


public interface IUserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
    void deleteById(UUID id);
    void update(UUID id, User user);
}
