package event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import util.MySQLStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventOnPlayerChat implements Listener {
    private final MySQLStorage mySQLStorage;
    private final Map<UUID, Boolean> awaitingClanName;
    private final Map<UUID, Integer> playerTaskIds;

    public EventOnPlayerChat(MySQLStorage mySQLStorage) {
        this.mySQLStorage = mySQLStorage;
        this.awaitingClanName = new HashMap<>();
        this.playerTaskIds = new HashMap<>();
    }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (awaitingClanName.containsKey(playerId)) {
            event.setCancelled(true);
            String clanName = event.getMessage();

            try {
                mySQLStorage.addClan(player.getName(), clanName);
                player.sendMessage("Клан '" + clanName + "' успешно создан!");
                System.out.println("Клан '" + clanName + "' создан для игрока " + player.getName());
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage("Произошла ошибка при создании клана.");
            }
            awaitingClanName.remove(playerId);

            if (playerTaskIds.containsKey(playerId)) {
                Bukkit.getScheduler().cancelTask(playerTaskIds.get(playerId));
                playerTaskIds.remove(playerId);
            }
        }
    }

    public void setAwaitingClanName(UUID playerId, boolean value) {
        awaitingClanName.put(playerId, value);
    }

    public void setPlayerTaskId(UUID playerId, int taskId) {
        playerTaskIds.put(playerId, taskId);
    }
}
