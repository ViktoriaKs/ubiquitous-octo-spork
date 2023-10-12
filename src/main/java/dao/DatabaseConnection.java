package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/postgres"; // Замените на свой URL
                String username = "postgres"; // Замените на свой логин
                String password = "87654321"; // Замените на свой пароль
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static boolean isDatabaseConnected() {
        if (connection != null) {
            try {
                return connection.isValid(5); // Проверяем соединение, ждем до 5 секунд
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}