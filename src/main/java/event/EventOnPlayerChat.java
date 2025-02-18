package event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.traiwy.clanpruginv2.ClanPruginV2;
import util.MySQLStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventOnPlayerChat implements Listener {

    private final MySQLStorage mySQLStorage;
    private final Map<UUID, Boolean> awaitingClanName = new HashMap<>();
    private final Map<UUID, Integer> playerTaskIds = new HashMap<>();
    private final ClanPruginV2 plugin;

    public EventOnPlayerChat(MySQLStorage mySQLStorage, ClanPruginV2 plugin) {
        this.mySQLStorage = mySQLStorage;
        this.plugin = plugin;
    }


    public void setAwaitingClanName(UUID playerId, boolean awaiting) {
        awaitingClanName.put(playerId, awaiting);
    }

    public boolean isAwaitingClanName(UUID playerId) {
        return awaitingClanName.getOrDefault(playerId, false);
    }

    public void setPlayerTaskId(UUID playerId, int taskId) {
        playerTaskIds.put(playerId, taskId);
    }

    public int getPlayerTaskId(UUID playerId) {
        return playerTaskIds.getOrDefault(playerId, -1);
    }

    public void removePlayerTaskId(UUID playerId) {
        playerTaskIds.remove(playerId);
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (isAwaitingClanName(playerId)) {
            event.setCancelled(true);
            String clanName = event.getMessage();

            int taskId = getPlayerTaskId(playerId);
            if (taskId != -1) {
                Bukkit.getScheduler().cancelTask(taskId);
                removePlayerTaskId(playerId);
            }

            setAwaitingClanName(playerId, false);


            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try {
                    mySQLStorage.addClan(player.getName(), clanName);
                    Bukkit.getScheduler().runTask(plugin, () -> {
                        player.sendMessage("Клан '" + clanName + "' успешно создан!");
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Bukkit.getScheduler().runTask(plugin, () -> {
                        player.sendMessage("Произошла ошибка при создании клана: " + e.getMessage());
                    });
                }
            });
        }
    }
}