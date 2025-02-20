package util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.traiwy.clanpruginv2.ClanPruginV2;

public class ConfigManager {

    public static void loadConfig() {
        FileConfiguration configuration = ClanPruginV2.getInstance().getConfig();

        MySQL.HOST = configuration.getString("database.host");
        MySQL.PORT = configuration.getInt("database.port");
        MySQL.USER = configuration.getString("database.user");
        MySQL.PASSWORD = configuration.getString("database.password");
        MySQL.DATABASE = configuration.getString("database.database");
    }

    public static class MySQL {
        public static String HOST = "localhost";
        public static int PORT = 3306;
        public static String USER = "root";
        public static String PASSWORD = "root";
        public static String DATABASE = "clanplugin";
    }
}