package org.example.repository.dao;

import org.example.model.Game;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {
    private final Connection connection;

    private static final String SELECT_BY_ID = "SELECT * FROM public.games WHERE id = ?";
    private static final String SQL_INSERT_GAME =
            """
                    INSERT INTO public.games(
                    name, type, rating, price)
                    VALUES(?, ?, ?, ?)
                    RETURNING id
                    """;
    private static final String REMOVE = "DELETE FROM public.games WHERE id = ?";
    private static final String UPDATE =
            """
                    UPDATE public.games
                    SET name=?, type=?, rating=?, price=?
                    WHERE id = ?
                    """;
    private static final String SELECT_ALL = "SELECT * FROM public.games";

    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Game get(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractGame(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Game save(Game game) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_GAME, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, game.getName());
            preparedStatement.setString(2, game.getType());
            preparedStatement.setInt(3, game.getRating());
            preparedStatement.setInt(4, game.getPrice());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                game.setId(resultSet.getInt(1));
            }

            return game;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Game game) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, game.getName());
            preparedStatement.setString(2, game.getType());
            preparedStatement.setInt(3, game.getRating());
            preparedStatement.setInt(4, game.getPrice());
            preparedStatement.setInt(5, game.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Game> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Game> games = new LinkedList<>();
            while (resultSet.next()) {
                games.add(extractGame(resultSet));
            }

            return games;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Game extractGame(ResultSet resultSet) throws SQLException {
        return Game.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .type(resultSet.getString("type"))
                .rating(resultSet.getInt("rating"))
                .price(resultSet.getInt("price"))
                .build();
    }
}
