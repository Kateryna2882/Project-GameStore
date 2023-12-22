package org.example.repository.dao;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(int id);
    Optional<User> findByNickname(String nickname);
}
