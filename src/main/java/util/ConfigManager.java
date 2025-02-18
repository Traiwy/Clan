package util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.traiwy.clanpruginv2.ClanPruginV2;

public class ConfigManager {
    private final ClanPruginV2 plugin;
    public ConfigManager(ClanPruginV2 plugin){
        this.plugin = plugin;
        load(plugin.getConfig());
        plugin.saveConfig();
    }
    public void reloadConfig() {
        plugin.reloadConfig();
        load(plugin.getConfig());
    }

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
