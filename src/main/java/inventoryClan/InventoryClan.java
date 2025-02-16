package inventoryClan;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import ru.traiwy.clanpruginv2.ClanPruginV2;

import java.util.ArrayList;
import java.util.List;



public class InventoryClan {
    private final ClanPruginV2 plugin;
    public InventoryClan(ClanPruginV2 plugin) {
        this.plugin = plugin;
    }

    public void CreateMenuClan(Player player){
        Inventory inventory = Bukkit.createInventory(null, 45, "Создание клана");
       ItemStack target = new ItemStack(Material.TARGET);
       ItemStack paper = new ItemStack(Material.PAPER);
       ItemStack arrow = new ItemStack(Material.ARROW);

        //Мишень
        ItemMeta metaTarget = target.getItemMeta();

        if (metaTarget != null) {
            metaTarget.displayName(Component.text("Создание клана")
                    .color(TextColor.fromHexString("#FF0000"))
                    .decoration(TextDecoration.ITALIC, false));
            List<String> loreTarget = new ArrayList<>();
            loreTarget.add("Создание клана");
            loreTarget.add("Лор клана");

            metaTarget.setLore(loreTarget);
            target.setItemMeta(metaTarget);
        }
        //Стрела
        ItemMeta metaArrow = arrow.getItemMeta();
        if (metaArrow != null) {
            metaArrow.displayName(Component.text("Выход из меню")
                    .color(TextColor.fromHexString("#FF0000"))
                    .decoration(TextDecoration.ITALIC, false));
            List<String> loreArrow = new ArrayList<>();
            loreArrow.add("Выход из меню");
            loreArrow.add("Лор бумаги");

            metaArrow.setLore(loreArrow);
            arrow.setItemMeta(metaArrow);
        }
        //Бумага
        ItemMeta metaPaper = paper.getItemMeta();
        if (metaPaper != null) {
            metaPaper.displayName(Component.text("Список клано")
                    .color(TextColor.fromHexString("#FF0000"))
                    .decoration(TextDecoration.ITALIC, false));
            List<String> lorePaper = new ArrayList<>();
            lorePaper.add("Выход из меню");
            lorePaper.add("Лор клана");

            metaPaper.setLore(lorePaper);
            paper.setItemMeta(metaPaper);
        }
        inventory.setItem(21, target);
        inventory.setItem(23, paper);
        inventory.setItem(44, arrow);

        player.openInventory(inventory);
    }

}
