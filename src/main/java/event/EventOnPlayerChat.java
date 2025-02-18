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
    private final Map<UUID, ClanCreationData> clanCreationDataMap = new HashMap<>();
    private final ClanPruginV2 plugin;

    public EventOnPlayerChat(MySQLStorage mySQLStorage, ClanPruginV2 plugin) {
        this.mySQLStorage = mySQLStorage;
        this.plugin = plugin;
    }


    public void startClanCreation(Player player) {
        ClanCreationData data = new ClanCreationData();
        data.setAwaitingClanName(true);
        int taskID = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            sendTextTitle(player);
        }, 0L, 20L).getTaskId();
        data.setTaskId(taskID);
        clanCreationDataMap.put(player.getUniqueId(), data);
    }

    public void cancelClanCreation(Player player) {
        ClanCreationData data = clanCreationDataMap.get(player.getUniqueId());
        if (data != null) {
            Bukkit.getScheduler().cancelTask(data.getTaskId());
            clanCreationDataMap.remove(player.getUniqueId());
        }
    }

    private ClanCreationData getClanCreationData(Player player) {
        return clanCreationDataMap.get(player.getUniqueId());
    }


    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        ClanCreationData data = getClanCreationData(player);
        if (data != null && data.isAwaitingClanName()) {
            event.setCancelled(true);
            String clanName = event.getMessage();
            cancelClanCreation(player);

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

    public void sendTextTitle(Player player){
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage( "§l§cВведите название клана");

    }

    private static class ClanCreationData {
        private boolean awaitingClanName = false;
        private int taskId = -1;

        public boolean isAwaitingClanName() {
            return awaitingClanName;
        }

        public void setAwaitingClanName(boolean awaitingClanName) {
            this.awaitingClanName = awaitingClanName;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }
    }
}