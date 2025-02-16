package util;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private void load(FileConfiguration file){
        MySQL.HOST = file.getString("database.host");
        MySQL.PORT = file.getInt("database.port");
        MySQL.USER = file.getString("database.user");
        MySQL.PASSWORD = file.getString("database.password");
        MySQL.DATABASE = file.getString("database.database");


    }
    public static class MySQL{
        public static String HOST = "localhost";
        public static int PORT = 3306;
        public static String USER = "root";
        public static String PASSWORD = "root";
        public static String DATABASE = "clanplugin";
    }
}
