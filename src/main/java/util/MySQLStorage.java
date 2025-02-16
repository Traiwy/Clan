package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MySQLStorage {
    private HikariDataSource mySqlManager;
    private static final Logger logger = Logger.getLogger(MySQLStorage.class.getName());

    public MySQLStorage() {
        try{
            String databaseUrl = "jdbc:mysql://" + ConfigManager.MySQL.HOST + ":" + ConfigManager.MySQL.PORT
                    + "/" + ConfigManager.MySQL.DATABASE + "?useSSL=false&allowPublicKeyRetrieval=true";
            final HikariConfig config = new HikariConfig();
            config.setJdbcUrl(databaseUrl);
            config.setUsername(ConfigManager.MySQL.USER);
            config.setPassword(ConfigManager.MySQL.PASSWORD);
            mySqlManager = new HikariDataSource(config);
            config.setMaximumPoolSize(10);
        }catch (Exception e) {
            logger.severe("Ошибка при инициализации базы данных: " + e.getMessage());
            return;
        }

        createTable();
    }

    public void close() {
        this.mySqlManager.close();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS players_clans (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "player_name VARCHAR(255) NOT NULL," +
                "clan_name VARCHAR(255) NOT NULL" +
                ");";

        try (Connection connection = mySqlManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
            System.out.println("Таблица 'players_clans' создана или уже существует.");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }
    public void addClan(String playerName, String clanName) {
        try (Connection connection = mySqlManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO players_clans (player_name, clan_name) VALUES (?, ?);"
             )) {
            statement.setString(1, playerName);
            statement.setString(2, clanName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}





