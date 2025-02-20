package event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.traiwy.clanpruginv2.ClanPruginV2;
import util.MySQLStorage;

import java.util.*;

public class EventOnPlayerChat implements Listener {

    private final MySQLStorage mySQLStorage;
    private static final Set<UUID> awaitingClanName = new HashSet<>();
    private final ClanPruginV2 plugin;

    public EventOnPlayerChat(MySQLStorage mySQLStorage, ClanPruginV2 plugin) {
        this.mySQLStorage = mySQLStorage;
        this.plugin = plugin;
    }


    public void startClanCreation(Player player) {
        UUID playerId = player.getUniqueId();
        awaitingClanName.add(playerId);
        if(awaitingClanName.contains(playerId)){
            Bukkit.getScheduler().runTaskTimer(plugin, () -> sendTextTitle(player), 0L, 20L);
            Bukkit.getLogger().info("startClanCreation: Started clan creation for " + player.getName());
        }else{
            Bukkit.getLogger().info("player not have in Hash awaitingClanName" + player.getName());
        }
    }

    public void cancelClanCreation(Player player) {
        awaitingClanName.remove(player.getUniqueId());
        Bukkit.getScheduler().cancelTasks(plugin);
        Bukkit.getLogger().info("cancelClanCreation: Canceled clan creation for " + player.getName());
    }


    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (awaitingClanName.contains(playerId)) {
            event.setCancelled(true);
            cancelClanCreation(player);
            String clanName = event.getMessage();
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try {
                    mySQLStorage.addClan(player.getName(), clanName);
                    Bukkit.getScheduler().runTask(plugin, () -> player.sendMessage("Клан успешно создан!"));
                } catch (Exception e) {
                    e.printStackTrace();
                    Bukkit.getScheduler().runTask(plugin, () -> player.sendMessage("Ошибка создания клана: " + e.getMessage()));
                }
            });
        }
    }

    public void sendTextTitle(Player player) {
        for(int i = 1; i < 10; i++){
            player.sendMessage(" ");
        }
        player.sendMessage("§l§cВведите название клана");
    }
}