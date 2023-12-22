package org.example.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GameStoreApp {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://your_database_url", "your_username", "your_password");

            // Ініціалізація репозиторіїв та сервісів
            UserRepository userRepository = new UserRepositoryImpl(connection);
            GameRepository gameRepository = new GameRepositoryImpl(connection);
            AccountRepository accountRepository = new AccountRepositoryImpl(connection);

            UserService userService = new UserService(userRepository, accountRepository);
            GameService gameService = new GameService(gameRepository, accountRepository);

            ConsoleApp consoleApp = new ConsoleApp(userService, gameService);
            consoleApp.start();

            // Закриття підключення після завершення роботи
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
