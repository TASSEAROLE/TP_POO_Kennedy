package tp2_poo2.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    // Configuration MySQL (pas SQLite !)
    private static final String URL = "jdbc:mysql://localhost:3306/stolen_devices_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Pilote MySQL
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Pilote MySQL introuvable", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}