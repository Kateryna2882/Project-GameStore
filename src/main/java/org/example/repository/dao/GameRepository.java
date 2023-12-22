package org.example.repository.dao;

import org.example.model.Game;

import java.util.List;

public interface GameRepository {
    Game get(int id);

    Game save(Game game);

    boolean remove(int id);

    int update(Game game);

    List<Game> findAll();
}
