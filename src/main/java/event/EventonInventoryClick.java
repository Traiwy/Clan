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

import java.util.UUID;


public class EventonInventoryClick implements Listener {

    private final ClanPruginV2 clanPruginV2;
    private final ClanViborMenu clanViborMenu;
    private final EventOnPlayerChat eventOnPlayerChat;

    public EventonInventoryClick(ClanPruginV2 clanPruginV2, EventOnPlayerChat eventOnPlayerChat) {
        this.clanPruginV2 = clanPruginV2;
        this.clanViborMenu = clanPruginV2.getClanViborMenu();
        this.eventOnPlayerChat = eventOnPlayerChat;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
            Player player = (Player) event.getWhoClicked();
            Inventory clickedInventory = event.getClickedInventory();
            ItemStack clickItem = event.getCurrentItem();

            if (event.getView().getTitle().equals("Выберите тип клана")) {
                if (clickItem != null) {
                    if (clickItem.getType() == Material.IRON_SWORD) {
                        if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 5)) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 5));
                            player.closeInventory();
                            Bukkit.getLogger().info("EventonInventoryClick: Calling startClanCreation for " + player.getName());
                            eventOnPlayerChat.startClanCreation(player);
                            event.setCancelled(true);
                        } else {
                            player.sendMessage("недостаточно средств, для создания кланов ( 5 алмазов )");
                            event.setCancelled(true);
                        }
                    } else if (clickItem.getType() == Material.IRON_HOE) {
                        event.setCancelled(true);
                    } else if (clickItem.getType() == Material.ARROW) {
                        player.closeInventory();
                        event.setCancelled(true);
                    }
                }
            } else if (clickItem != null) {
                if (clickItem.getType() == Material.TARGET) {
                    clanViborMenu.CreateChoiseInventory(player);
                    event.setCancelled(true);
                } else if (clickItem.getType() == Material.PAPER || clickItem.getType() == Material.ARROW) {
                    event.setCancelled(true);
                    player.closeInventory();
                }
            }

        }
    }