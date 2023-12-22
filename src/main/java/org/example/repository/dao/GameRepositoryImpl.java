package org.example.repository.dao;
import org.example.model.User;

import java.sql.Connection;
import java.util.Optional;

public class GameRepositoryImpl implements UserRepository {

    private final Connection connection;

    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User save(User user) {
        // Реалізація збереження користувача в базі даних
        // ...
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        // Реалізація пошуку користувача за id в базі даних
        // ...
        return Optional.empty();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        // Реалізація пошуку користувача за nickname в базі даних
        // ...
        return Optional.empty();
    }
}
