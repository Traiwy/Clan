package event;

import inventoryClan.ClanViborMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.traiwy.clanpruginv2.ClanPruginV2;


public class EventonInventoryClick implements Listener {

    private final ClanPruginV2 clanPruginV2;
    private final ClanViborMenu clanViborMenu;
    private final EventOnPlayerChat eventOnPlayerChat;
    private final int PROTECTED_SLOT = 21;
    private final int PROTECTED_SLOT2 = 23;

    public EventonInventoryClick(ClanPruginV2 clanPruginV2) {
        this.clanPruginV2 = clanPruginV2;
        this.clanViborMenu = clanPruginV2.getClanViborMenu();
        this.eventOnPlayerChat = new EventOnPlayerChat(clanPruginV2.getMySQLStorage());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickItem = event.getCurrentItem();

        if (clickedInventory != null || event.getView().getTitle().equals("Создание клана")) {
            if (clickedInventory != null && event.getView().getTitle().equals("Выберите тип клана")) {
                event.setCancelled(true);
                if (clickItem != null) {
                    if (clickItem.getType() == Material.IRON_SWORD) {
                        if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 5)) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 5));
                            clickedInventory.close();
                            player.closeInventory();

                            eventOnPlayerChat.setAwaitingClanName(player.getUniqueId(), true);
                            int taskID = Bukkit.getScheduler().runTaskTimer(clanPruginV2, () -> {
                                sendTextTitle(player);
                            }, 0L, 20L).getTaskId();
                            eventOnPlayerChat.setPlayerTaskId(player.getUniqueId(), taskID);
                        } else {
                            player.sendMessage("недостаточно средств, для создания кланов ( 5 алмазов )");
                        }
                    }
                    if (clickItem.getType() == Material.IRON_HOE) {
                        event.setCancelled(true);
                    }
                    if (clickItem.getType() == Material.ARROW) {
                        clickedInventory.close();
                    }
                }
            }
        }
        if (clickItem != null && clickItem.getType() == Material.TARGET) {
            clanViborMenu.CreateChoiseInventory(player);
            event.setCancelled(true);
        }
        if (clickItem != null && clickItem.getType() == Material.PAPER) {
            event.setCancelled(true);
        }
        if (clickItem != null && clickItem.getType() == Material.ARROW) {
            event.setCancelled(true);
            clickedInventory.close();
        }
        event.setCancelled(true);
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
}
